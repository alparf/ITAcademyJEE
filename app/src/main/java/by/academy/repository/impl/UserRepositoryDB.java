package by.academy.repository.impl;

import by.academy.constant.SqlConstant;
import by.academy.exception.AppException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.pool.ConnectionManager;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;
import by.academy.specification.ISqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryDB implements IRepository<User> {

    @Override
    public boolean addUser(User user) {
        final int FIO = 1;
        final int AGE = 2;
        final int USER_NAME = 3;
        final int PASSWORD = 4;
        final int USER_TYPE = 5;
        int update = 0;
        Connection connection = ConnectionManager.getPoll().get();
        try (PreparedStatement statement = connection.prepareStatement(SqlConstant.INSERT_USER)) {
            if (null != user) {
                statement.setString(FIO, user.getFio());
                statement.setInt(AGE, user.getAge());
                statement.setString(USER_NAME, user.getUserName());
                statement.setString(PASSWORD, user.getPassword());
                statement.setString(USER_TYPE, user.getUserType().getName());
                update = statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
        return update > 0;
    }

    @Override
    public boolean removeUser(User user) {
        final int ID = 1;
        int update = 0;
        Connection connection = ConnectionManager.getPoll().get();
        try (PreparedStatement statement = connection.prepareStatement(SqlConstant.DELETE_USER_BY_ID)) {
            if (null != user) {
                statement.setLong(ID, user.getId());
                update = statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
        return update > 0;
    }

    @Override
    public boolean setUser(User user) {
        return false;
    }

    @Override
    public List<User> query(ISpecification<User> specification) {
        List<User> users = new LinkedList<>();
        if (specification instanceof ISqlSpecification) {
            final int ID = 1;
            final int FIO = 2;
            final int AGE = 3;
            final int USER_NAME = 4;
            final int PASSWORD = 5;
            final int USER_TYPE = 6;
            ISqlSpecification sql = (ISqlSpecification) specification;
            Connection connection = ConnectionManager.getPoll().get();
            try (PreparedStatement statement = sql.getPreparedStatement(connection);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(User.newBuilder()
                            .withId(resultSet.getLong(ID))
                            .withFio(resultSet.getString(FIO))
                            .withAge(resultSet.getInt(AGE))
                            .withUserName(resultSet.getString(USER_NAME))
                            .withPassword(resultSet.getString(PASSWORD))
                            .withUserType(UserType.valueOf(resultSet.getString(USER_TYPE)))
                            .build());
                }
            } catch (SQLException e) {
                throw new AppException(e.getMessage());
            } finally {
                ConnectionManager.getPoll().put(connection);
            }
        }
        users.removeIf(user -> !specification.specification(user));
        return users;
    }
}
