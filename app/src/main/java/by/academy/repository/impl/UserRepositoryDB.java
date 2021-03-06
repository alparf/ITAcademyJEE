package by.academy.repository.impl;

import by.academy.connection.ConnectionManager;
import by.academy.constant.SqlQuery;
import by.academy.exception.AppException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;
import by.academy.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryDB implements IRepository<User> {

    @Override
    public boolean add(User user) {
        final int FIO = 1;
        final int AGE = 2;
        final int USER_NAME = 3;
        final int PASSWORD = 4;
        final int USER_TYPE = 5;
        int update = 0;
        try {
            Connection connection = ConnectionManager.getConnectionPool().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER)){
                if (null != user) {
                    statement.setString(FIO, user.getFio());
                    statement.setInt(AGE, user.getAge());
                    statement.setString(USER_NAME, user.getUserName());
                    statement.setString(PASSWORD, user.getPassword());
                    statement.setString(USER_TYPE, user.getUserType().getName());
                    update = statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new AppException(e);
        }
        return update > 0;
    }

    @Override
    public boolean remove(User user) {
        final int ID = 1;
        int update = 0;
        try {
            Connection connection = ConnectionManager.getConnectionPool().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_USER_BY_ID)) {
                if (null != user) {
                    statement.setLong(ID, user.getId());
                    update = statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new AppException(e);
        }
        return update > 0;
    }

    @Override
    public boolean set(User user) {
        return false;
    }

    @Override
    public List<User> query(ISpecification<User> specification) {
        List<User> users = new LinkedList<>();
        final int ID = 1;
        final int FIO = 2;
        final int AGE = 3;
        final int USER_NAME = 4;
        final int PASSWORD = 5;
        final int USER_TYPE = 6;
        if (specification instanceof SqlSpecification) {
            SqlSpecification sqlSpecification = (SqlSpecification) specification;
            try {
                Connection connection = ConnectionManager.getConnectionPool().getConnection();
                try (PreparedStatement statement = sqlSpecification.getPreparedStatement(connection);
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
                }
            } catch (SQLException e) {
                throw new AppException(e);
            }
        }
        users.removeIf(specification::isSpecific);
        return users;
    }
}
