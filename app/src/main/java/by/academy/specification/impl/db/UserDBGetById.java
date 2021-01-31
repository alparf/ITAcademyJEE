package by.academy.specification.impl.db;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDBGetById implements ISpecification<User> {

    private final long userId;

    public UserDBGetById(long userId) {
        this.userId = userId;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        final int USER_ID = 1;
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(SqlConstant.SELECT_USER_BY_ID);
        preparedStatement.setLong(USER_ID, this.userId);
        return preparedStatement;
    }

    @Override
    public boolean specification(User user) {
        return true;
    }
}
