package by.academy.pool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ConnectionManager {

    private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);

    public static ConnectionPool getPoll() {
        return ConnectionHolder.CONNECTION_POOL;
    }

    public static class ConnectionHolder {

        public static final ConnectionPool CONNECTION_POOL;

        static {
            Properties properties = new Properties();
            try {
                properties.load(Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("connection.prop"));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            CONNECTION_POOL = new ConnectionPool(
                    properties.getProperty("DB_DRIVER"),
                    properties.getProperty("URL"),
                    properties.getProperty("USER"),
                    properties.getProperty("PASSWORD"),
                    Long.parseLong(properties.getProperty("LIVE_TIME")));
        }
    }
}
