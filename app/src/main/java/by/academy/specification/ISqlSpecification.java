package by.academy.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ISqlSpecification {
    PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
}
