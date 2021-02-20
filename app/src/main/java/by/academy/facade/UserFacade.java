package by.academy.facade;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import java.util.*;

public class UserFacade {
    public static Optional<User> login(String userName, String password) {
        IUserService service = new UserService();
        password = Base64.getEncoder().encodeToString(password.getBytes());
        return service.findUser(userName, password);
    }

    public static List<User> getAll() {
        IUserService service = new UserService();
        return service.findAllUsers();
    }

    public static List<User> getAll(UserType userType) {
        IUserService service = new UserService();
        return service.findAllUsers(userType);
    }

    public static Optional<User> newUser(User user) {
        IUserService service = new UserService();
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        return service.addUser(user);
    }

    public static Optional<User> remove(long id) {
        IUserService service = new UserService();
        return service.removeUser(id);
    }
}
