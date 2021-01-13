package by.academy.specification.impl.db;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.Coach;
import by.academy.specification.ICoachSpecification;
import by.academy.specification.ISqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CoachDBGetAll implements ICoachSpecification, ISqlSpecification {

    @Override
    public boolean specification(Coach coach) {
        return true;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(SqlConstant.SELECT_COACHES);
    }
}
