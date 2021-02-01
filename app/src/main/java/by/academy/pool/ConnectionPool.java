package by.academy.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool extends AbstractPoll<Connection> {
    private final String url;
    private final String user;
    private final String password;

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);

    protected ConnectionPool(String url, String user, String password, long liveTime) {
        super(liveTime);
        this.url = url;
        this.user = user;
        this.password = password;
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
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(this.url, this.user, this.password);
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
