package by.academy.model.factory;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.security.PassAuth;

public class UserFactory {

    public static final PassAuth PASS_AUTH = new PassAuth();

    public static User createUser(String fio, int age, String userName, String password, UserType userType) {
        String hashedPassword = null;
        if(null != password) {
            hashedPassword = PASS_AUTH.hash(password.toCharArray());
        }
        return new User(fio, age, userName, hashedPassword, userType);
    }

    public static User createUser() {
        return new User(null, 0, null, null, null);
    }
}
