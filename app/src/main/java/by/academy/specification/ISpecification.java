package by.academy.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ISpecification<T> {
    default PreparedStatement getPreparedStatement(Connection connection) throws SQLException {
        return null;
    }
    boolean specification(T t);
}
