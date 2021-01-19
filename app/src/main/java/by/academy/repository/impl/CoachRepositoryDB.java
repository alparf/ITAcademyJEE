package by.academy.repository.impl;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.Coach;
import by.academy.model.bean.UserType;
import by.academy.model.builder.impl.UserBuilder;
import by.academy.model.factory.CoachFactory;
import by.academy.pool.ConnectionManager;
import by.academy.repository.ICoachRepository;
import by.academy.specification.ISpecification;
import by.academy.specification.ISqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CoachRepositoryDB implements ICoachRepository {

    @Override
    public boolean addSalary(Coach coach, int salary) {
        final int USER_ID = 1;
        final int SALARY = 2;
        int update = 0;
        Connection connection = ConnectionManager.getPoll().get();
        try (PreparedStatement statement = connection.prepareStatement(SqlConstant.INSERT_SALARY)) {
            if ((null != coach) && (null != coach.getUser())) {
                statement.setLong(USER_ID, coach.getUser().getId());
                statement.setInt(SALARY, salary);
                update = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
        return update > 0;
    }

    @Override
    public List<Coach> query(ISpecification<Coach> specification) {
        List<Coach> coaches = new LinkedList<>();
        if (specification instanceof ISqlSpecification) {
            final int ID = 1;
            final int FIO = 2;
            final int AGE = 3;
            final int USER_NAME = 4;
            final int PASSWORD = 5;
            final int USER_TYPE = 6;
            ISqlSpecification sql = (ISqlSpecification) specification;
            Connection connection = ConnectionManager.getPoll().get();
            try (PreparedStatement userPreparedStatement = sql.getPreparedStatement(connection);
                 ResultSet resultSet = userPreparedStatement.executeQuery()) {
                UserBuilder user = new UserBuilder();
                while (resultSet.next()) {
                    user.withId(resultSet.getLong(ID))
                            .withFio(resultSet.getString(FIO))
                            .withAge(resultSet.getInt(AGE))
                            .withUserName(resultSet.getString(USER_NAME))
                            .withPassword(resultSet.getString(PASSWORD))
                            .withUserType(UserType.valueOf(resultSet.getString(USER_TYPE)));
                    coaches.add(CoachFactory.createCoach(user.build()));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.getPoll().put(connection);
            }
            for (Coach coach: coaches) {
                coach.setSalaries(getSalaryList(coach.getUser().getId()));
            }
        }
        coaches.removeIf(coach -> !specification.specification(coach));
        return coaches;
    }

    private Deque<Integer> getSalaryList(long userId) {
        final int USER_ID = 1;
        final int SALARY = 1;
        Deque<Integer> salaries = new LinkedList<>();
        Connection connection = ConnectionManager.getPoll().get();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SELECT_SALARY)) {
            preparedStatement.setLong(USER_ID, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salaries.addFirst(resultSet.getInt(SALARY));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getPoll().put(connection);
            closeResultSet(resultSet);
        }
        return salaries;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
