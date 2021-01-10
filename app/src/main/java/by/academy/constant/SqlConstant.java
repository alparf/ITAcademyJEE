package by.academy.constant;

public class SqlConstant {
    public static final String INSERT_USER = "INSERT INTO users (\"fio\", \"age\", \"userName\", \"password\", \"userType\") " +
                                             "VALUES(?, ?, ?, ?, ?);";
    public static final String SELECT_USER = "SELECT * FROM users " +
                                             "WHERE \"userName\" = ?;";
    public static final String DELETE_USER = "DELETE FROM users WHERE \"id\" = ?);";
    public static final String SELECT_USERS = "SELECT * FROM users;";
}
