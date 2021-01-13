package by.academy.constant;

public class SqlConstant {
    public static final String INSERT_USER = "INSERT INTO users (\"fio\", \"age\", \"userName\", \"password\", \"userType\") " +
                                             "VALUES (?, ?, ?, ?, ?);";
    public static final String SELECT_USER_BY_USER_NAME = "SELECT * FROM users " +
                                                          "WHERE \"userName\" = ?;";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users " +
                                                   "WHERE \"id\" = ?;";

    public static final String SELECT_USERS = "SELECT * FROM users;";

    public static final String SELECT_COACHES = "SELECT * FROM users " +
                                                "WHERE \"userType\" = 'COACH';";

    public static final String INSERT_SALARY = "INSERT INTO salaries (\"userId\", \"salary\") " +
                                               "VALUES (?, ?)";
    public static final String SELECT_SALARY = "SELECT \"salary\" FROM salaries " +
                                               "WHERE \"userId\" = ?;";
}
