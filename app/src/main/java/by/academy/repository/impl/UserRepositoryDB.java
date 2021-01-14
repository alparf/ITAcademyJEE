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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryDB implements IUserRepository {

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
            e.printStackTrace();
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
        return update > 0 ? true : false;
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
            e.printStackTrace();
        } finally {
            ConnectionManager.getPoll().put(connection);
        }
        return update > 0 ? true : false;
    }

    @Override
    public boolean setUser(User user) {
        return false;
    }

    @Override
    public List<User> query(IUserSpecification specification) {
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
                User user;
                while (resultSet.next()) {
                    user = UserFactory.createUser(
                            resultSet.getLong(ID),
                            resultSet.getString(FIO),
                            resultSet.getInt(AGE),
                            resultSet.getString(USER_NAME),
                            resultSet.getString(PASSWORD),
                            UserType.valueOf(resultSet.getString(USER_TYPE))
                    );
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.getPoll().put(connection);
            }
        }
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (!specification.specification(iterator.next())) {
                iterator.remove();
            }
        }
        return users;
    }
}
