package by.academy.repository.impl;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import by.academy.pool.ConnectionManager;
import by.academy.repository.IUserRepository;
import by.academy.specification.ISqlSpecification;
import by.academy.specification.IUserSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryDB implements IUserRepository {

    @Override
    public void addUser(User user) {
        final int FIO = 1;
        final int AGE = 2;
        final int USER_NAME = 3;
        final int PASSWORD = 4;
        final int USER_TYPE = 5;
        Connection connection = ConnectionManager.getPoll().get();
        try (PreparedStatement statement = connection.prepareStatement(SqlConstant.INSERT_USER)) {
            if (null != user) {
                statement.setString(FIO, user.getFio());
                statement.setInt(AGE, user.getAge());
                statement.setString(USER_NAME, user.getUserName());
                statement.setString(PASSWORD, user.getPassword());
                statement.setString(USER_TYPE, user.getUserType().getName());
                int rows = statement.executeUpdate();
                System.out.println(rows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void setUser(User user) {

    }

    @Override
    public List<User> query(IUserSpecification specification) {
        List<User> resultSetResults = new LinkedList<>();
        if (specification instanceof ISqlSpecification) {
            ISqlSpecification sql = (ISqlSpecification) specification;
            final int ID = 1;
            final int FIO = 2;
            final int AGE = 3;
            final int USER_NAME = 4;
            final int PASSWORD = 5;
            final int USER_TYPE = 6;
            User user = null;
            Connection connection = ConnectionManager.getPoll().get();
            try (PreparedStatement statement = connection.prepareStatement(sql.toSqlCauses());
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = UserFactory.createUser(
                            resultSet.getLong(ID),
                            resultSet.getString(FIO),
                            resultSet.getInt(AGE),
                            resultSet.getString(USER_NAME),
                            resultSet.getString(PASSWORD),
                            UserType.valueOf(resultSet.getString(USER_TYPE))
                    );
                    resultSetResults.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.getPoll().put(connection);
            }
        }
        List<User> userList = new LinkedList<>();
        for(User user: resultSetResults) {
            if (specification.specification(user)) {
                userList.add(user);
            }
        }
        return userList;
    }
}
