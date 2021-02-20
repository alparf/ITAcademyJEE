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
        return service.getUserByUserNameAndPassword(userName, password);
    }

    public static List<User> getAll() {
        IUserService service = new UserService();
        return service.getAll();
    }

    public static List<User> getAll(UserType userType) {
        IUserService service = new UserService();
        return service.getAll(userType);
    }

    public static Optional<User> newUser(User user) {
        IUserService service = new UserService();
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        return service.newUser(user);
    }

    public static Optional<User> remove(long id) {
        IUserService service = new UserService();
        return service.remove(id);
    }
}
