package by.academy.specification.impl;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.Coach;
import by.academy.specification.ISpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CoachDBGetAll implements ISpecification<Coach> {

    @Override
    public boolean specification(Coach coach) {
        return true;
    }

    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SqlConstant.SELECT_COACHES);
    }
}
