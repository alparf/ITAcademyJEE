package by.academy.connection;

import by.academy.exception.AppException;
import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.sql.PooledConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {

    private static final int DEFAULT_TIME_OUT = 60_000;
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_TIME_OUT = "connection.time.out";
    private static final String PROPERTIES_FILE_NAME = "connection.prop";

    private static volatile PooledConnection pooledConnection;

    public static PooledConnection getConnectionPool() {
        PooledConnection connection = pooledConnection;
        if (null == connection) {
            synchronized (ConnectionManager.class) {
                connection = pooledConnection;
                if (null == connection) {
                    Properties properties = loadProps(PROPERTIES_FILE_NAME);
                    PGConnectionPoolDataSource dataSource = new PGConnectionPoolDataSource();
                    dataSource.setURL(properties.getProperty(URL));
                    dataSource.setUser(properties.getProperty(USER));
                    dataSource.setPassword(properties.getProperty(PASSWORD));
                    try {
                        dataSource.setConnectTimeout(
                                Integer.parseInt(properties.getProperty(CONNECTION_TIME_OUT)));
                    } catch (NumberFormatException e) {
                        dataSource.setConnectTimeout(DEFAULT_TIME_OUT);
                    }
                    try {
                        pooledConnection = dataSource.getPooledConnection();
                    } catch (SQLException e) {
                        throw new AppException(e.getMessage());
                    }
                }
            }
        }
        return pooledConnection;
    }

    private static Properties loadProps(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private ConnectionManager () {
        
    }
}
