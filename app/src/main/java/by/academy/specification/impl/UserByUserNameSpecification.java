package by.academy.specification.impl;

import by.academy.constant.SqlQuery;
import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserByUserNameSpecification implements ISpecification<User> {

    private final String userName;

    public UserByUserNameSpecification(String userName) {
        this.userName = userName;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        final int USER_NAME = 1;
        PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_USER_NAME);
        preparedStatement.setString(USER_NAME, this.userName);
        return preparedStatement;
    }

    @Override
    public boolean specificity(User user) {
        return false;
    }
}
