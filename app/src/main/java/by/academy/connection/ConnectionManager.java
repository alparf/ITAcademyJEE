package by.academy.connection;

import by.academy.exception.AppException;
import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.sql.PooledConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    public static PooledConnection getConnectionPool() {
        return PoolHolder.pooledConnection;
    }

    public static class PoolHolder {
        private static final int DEFAULT_TIME_OUT = 60_000;

        public static final PooledConnection pooledConnection;

        static {
            Properties properties = new Properties();
            loadProps(properties);
            PGConnectionPoolDataSource dataSource = new PGConnectionPoolDataSource();
            dataSource.setURL(properties.getProperty("url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            System.out.println("url: " + properties.getProperty("url"));
            try {
                dataSource.setConnectTimeout(
                        Integer.parseInt(properties.getProperty("connection.time.out")));
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

    private static Properties loadProps(Properties properties) {
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("connection.prop"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
