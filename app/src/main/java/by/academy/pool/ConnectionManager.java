package by.academy.pool;

import by.academy.constant.DBConstant;

public class ConnectionManager {

    private static volatile ConnectionPool instance;

    public static ConnectionPool getPoll() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool(
                            DBConstant.URL, DBConstant.USER, DBConstant.PASSWORD);
                }
            }
        }
        return localInstance;
    }
}
