package by.academy.repository.impl;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import by.academy.pool.ConnectionManager;
import by.academy.repository.ICoachRepository;
import by.academy.specification.ICoachSpecification;
import by.academy.specification.ISqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CoachRepositoryDB implements ICoachRepository {

    @Override
    public void addCoach(Coach coach) {

    }

    @Override
    public void removeCoach(Coach coach) {

    }

    @Override
    public void setCoach(Coach coach) {

    }

    @Override
    public List<Coach> query(ICoachSpecification specification) {
        List<Coach> coaches = new LinkedList<>();
        if (specification instanceof ISqlSpecification) {
            ISqlSpecification sql = (ISqlSpecification) specification;
            Connection connection = ConnectionManager.getPoll().get();
            try (PreparedStatement preparedStatement = sql.getPreparedStatement(connection);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionManager.getPoll().put(connection);
            }
        }
        Iterator<Coach> iterator = coaches.iterator();
        while (iterator.hasNext()) {
            if (!specification.specification(iterator.next())) {
                iterator.remove();
            }
        }
        return coaches;
    }
}
