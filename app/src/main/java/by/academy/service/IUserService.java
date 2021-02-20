package by.academy.service;

import by.academy.model.bean.User;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.UserType;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByUserNameAndPassword(String userName, String password);
    List<User> getAll();
    List<User> getAll(UserType userType);
    Optional<User> getUser(long id);
    Optional<User> newUser(User user) throws UserServiceException;
    Optional<User> remove(long id);
}
