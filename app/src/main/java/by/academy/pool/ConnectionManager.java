package by.academy.pool;

import by.academy.constant.DBConstant;

public class ConnectionManager {

    public static class ConnectionHolder {
        public static final ConnectionPool CONNECTION_POOL = new ConnectionPool(
                DBConstant.URL,
                DBConstant.USER,
                DBConstant.PASSWORD);
    }

    public static ConnectionPool getPoll() {
        return ConnectionHolder.CONNECTION_POOL;
    }
}
