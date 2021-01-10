package by.academy.model.factory;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class UserFactory {

    public static User createUser(long id, String fio, int age, String userName, String password, UserType userType) {
        return new User(id, fio, age, userName, password, userType);
    }

    public static User createUser() {
        return new User(0, null, 0, null, null, null);
    }
}
