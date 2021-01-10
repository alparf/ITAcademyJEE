package by.academy.service;

import by.academy.model.bean.User;
import by.academy.exception.UserServiceException;

import java.util.List;

public interface IUserService {
    User login(String userName, String password);
    List<User> getAll();
    void addUser(User user) throws UserServiceException;
    void removeUserById(long id);
}
