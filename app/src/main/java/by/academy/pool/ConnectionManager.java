package by.academy.pool;


import java.io.IOException;
import java.util.Properties;

public class ConnectionManager {

    public static class ConnectionHolder {
        public static final ConnectionPool CONNECTION_POOL;
        static {
            Properties properties = new Properties();
            try {
                properties.load(Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("connection.prop"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            CONNECTION_POOL = new ConnectionPool(
                    properties.getProperty("URL"),
                    properties.getProperty("USER"),
                    properties.getProperty("PASSWORD"),
                    Long.parseLong(properties.getProperty("LIVE_TIME")));
        }
    }

    public static ConnectionPool getPoll() {
        return ConnectionHolder.CONNECTION_POOL;
    }
}
