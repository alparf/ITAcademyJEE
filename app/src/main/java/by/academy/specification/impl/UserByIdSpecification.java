package by.academy.specification.impl;

import by.academy.constant.SqlQuery;
import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserByIdSpecification implements ISpecification<User> {

    private final long userId;

    public UserByIdSpecification(long userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        final int USER_ID = 1;
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID);
        preparedStatement.setLong(USER_ID, this.userId);
        return preparedStatement;
    }

    @Override
    public boolean specificity(User user) {
        return false;
    }
}
