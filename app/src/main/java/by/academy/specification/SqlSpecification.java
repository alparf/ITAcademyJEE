package by.academy.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlSpecification {
    PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
}
