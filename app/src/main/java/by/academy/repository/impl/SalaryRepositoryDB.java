package by.academy.repository.impl;

import by.academy.constant.SqlQuery;
import by.academy.exception.AppException;
import by.academy.model.bean.Salary;
import by.academy.connection.ConnectionManager;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;
import by.academy.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SalaryRepositoryDB implements IRepository<Salary> {

    @Override
    public boolean add(Salary salary) {
        int update = 0;
        final int COACH = 1;
        final int VALUE = 2;
        try {
            Connection connection = ConnectionManager.getConnectionPool().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_SALARY)) {
                if ((null != salary) && (null != salary.getCoach())) {
                    statement.setLong(COACH, salary.getCoach().getId());
                    statement.setInt(VALUE, salary.getValue());
                    update = statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new AppException(e);
        }
        return update > 0;
    }

    @Override
    public boolean remove(Salary salary) {
        return false;
    }

    @Override
    public boolean set(Salary salary) {
        return false;
    }

    @Override
    public List<Salary> query(ISpecification<Salary> specification) {
        final int ID = 1;
        final int VALUE = 2;
        List<Salary> salaries = new LinkedList<>();
        if (specification instanceof SqlSpecification) {
            SqlSpecification sqlSpecification = (SqlSpecification) specification;
            try {
                Connection connection = ConnectionManager.getConnectionPool().getConnection();
                try (PreparedStatement statement = sqlSpecification.getPreparedStatement(connection);
                     ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        salaries.add(Salary.newBuilder()
                                .withId(resultSet.getLong(ID))
                                .withValue(resultSet.getInt(VALUE))
                                .build());
                    }
                }
            } catch (SQLException e) {
                throw new AppException(e);
            }
        }
        salaries.removeIf(specification::isSpecific);
        return salaries;
    }
}
