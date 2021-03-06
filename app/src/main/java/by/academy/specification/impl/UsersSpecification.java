package by.academy.specification.impl;

import by.academy.constant.SqlQuery;
import by.academy.model.bean.User;
import by.academy.specification.ISpecification;
import by.academy.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersSpecification implements ISpecification<User>, SqlSpecification {

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SqlQuery.SELECT_USERS);
    }

    @Override
    public boolean isSpecific(User user) {
        return false;
    }
}
