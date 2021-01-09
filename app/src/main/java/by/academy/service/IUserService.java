package by.academy.service;

import by.academy.model.bean.User;
import by.academy.exception.UserServiceException;

import java.util.List;

public interface IUserService {
    User login(String userName, String password);
    List<User> getAll();
    User getUserByName(String userName);
    void addUser(User user) throws UserServiceException;
    void removeUser(String userName);
}
