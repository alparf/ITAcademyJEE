package by.academy.service;

import by.academy.model.bean.User;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.UserType;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> addUser(User user) throws UserServiceException;
    Optional<User> removeUser(long id);
    Optional<User> findUser(String userName, String password);
    Optional<User> findUser(long id);
    List<User> findAllUsers();
    List<User> findAllUsers(UserType userType);
}
