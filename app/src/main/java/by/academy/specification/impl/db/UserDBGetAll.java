package by.academy.specification.impl.db;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.User;
import by.academy.specification.ISpecification;
import by.academy.specification.ISqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDBGetAll implements ISpecification<User>, ISqlSpecification {

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SqlConstant.SELECT_USERS);
    }

    @Override
    public boolean specification(User user) {
        return true;
    }
}
