package by.academy.repository.impl;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.CoachFactory;
import by.academy.model.factory.UserFactory;
import by.academy.pool.ConnectionManager;
import by.academy.repository.ICoachRepository;
import by.academy.specification.ICoachSpecification;
import by.academy.specification.ISqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CoachRepositoryDB implements ICoachRepository {

    @Override
    public void addSalary(Coach coach, int salary) {
        final int USER_ID = 1;
        final int SALARY = 2;
        Connection connection = ConnectionManager.getPoll().get();
        try (PreparedStatement statement = connection.prepareStatement(SqlConstant.INSERT_SALARY)) {
            if ((null != coach) && (null != coach.getUser())) {
                statement.setLong(USER_ID, coach.getUser().getId());
                statement.setInt(SALARY, salary);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
    }

    @Override
    public List<Coach> query(ICoachSpecification specification) {
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
            try (PreparedStatement preparedStatement = sql.getPreparedStatement(connection);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                User user;
                while (resultSet.next()) {
                    user = UserFactory.createUser(

                            resultSet.getLong(ID),
                            resultSet.getString(FIO),
                            resultSet.getInt(AGE),
                            resultSet.getString(USER_NAME),
                            resultSet.getString(PASSWORD),
                            UserType.valueOf(resultSet.getString(USER_TYPE)));
                    coaches.add(CoachFactory.createCoach(user));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.getPoll().put(connection);
            }
        }
        Iterator<Coach> iterator = coaches.iterator();
        while (iterator.hasNext()) {
            if (!specification.specification(iterator.next())) {
                iterator.remove();
            }
        }
        return coaches;
    }
}
