package by.academy.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool extends AbstractPoll<Connection> {
    private final String url;
    private final String user;
    private final String password;

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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected boolean validate(Connection connection) {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
