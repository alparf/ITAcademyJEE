package by.academy.model.factory;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class UserFactory {
    public static User createUser(String fio, int age, String userName, String password, UserType userType) {
        return new User(fio, age, userName, password, userType);
    }
}
