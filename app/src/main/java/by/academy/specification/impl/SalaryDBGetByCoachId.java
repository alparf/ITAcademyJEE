package by.academy.specification.impl;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.Salary;
import by.academy.specification.ISpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalaryDBGetByCoachId implements ISpecification<Salary> {

    private final long coachId;

    public SalaryDBGetByCoachId(long coachId) {
        this.coachId = coachId;
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        final int COACH_ID = 1;
        PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SELECT_SALARY);
        preparedStatement.setLong(COACH_ID, coachId);
        return preparedStatement;
    }

    @Override
    public boolean specificity(Salary salary) {
        return false;
    }
}
