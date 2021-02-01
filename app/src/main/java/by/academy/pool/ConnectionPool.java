package by.academy.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool extends AbstractPoll<Connection> {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final String DRIVER;

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);

    protected ConnectionPool(String driver, String url, String user, String password, long liveTime) {
        super(liveTime);
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
        this.DRIVER = driver;
    }

    public Connection get() {
        synchronized (ConnectionPool.class) {
            long currentTime = System.currentTimeMillis();
            if (this.unLock.size() > 0) {
                for (Connection connection : this.unLock.keySet()) {
                    if (currentTime - this.unLock.get(connection) > this.liveTime) {
                        this.unLock.remove(connection);
                        close(connection);
                    } else {
                        if (this.validate(connection)) {
                            this.unLock.remove(connection);
                            this.lock.put(connection, currentTime);
                            return connection;
                        } else {
                            this.unLock.remove(connection);
                            close(connection);
                        }
                    }
                }
            }
        }
        Connection newConnection = open();
        this.lock.put(newConnection, System.currentTimeMillis());
        return newConnection;
    }

    public void put(Connection connection) {
        this.lock.remove(connection);
        this.unLock.put(connection, System.currentTimeMillis());
    }

    protected Connection open() {
        try {
            Class.forName(this.DRIVER);
            return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    protected void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    protected boolean validate(Connection connection) {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
