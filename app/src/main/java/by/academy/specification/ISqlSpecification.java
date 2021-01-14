package by.academy.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ISqlSpecification extends IUserSpecification {
    PreparedStatement getPreparedStatement(Connection connection) throws SQLException;
}
