package by.academy.facade;

import by.academy.model.bean.User;
import by.academy.service.impl.UserService;

import java.util.*;

public class UserFacade {
    public static Optional<User> login(String userName, String password) {
        password = Base64.getEncoder().encodeToString(password.getBytes());
        return UserService.getService().findUser(userName, password);
    }

    public static List<User> getAll() {
        return UserService.getService().findAllUsers();
    }

    public static Optional<User> newUser(User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        return UserService.getService().addUser(user);
    }

    public static Optional<User> remove(long id) {
        return UserService.getService().removeUser(id);
    }
}
