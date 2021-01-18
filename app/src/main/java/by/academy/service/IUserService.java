package by.academy.service;

import by.academy.model.bean.User;
import by.academy.exception.UserServiceException;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByUserNameAndPassword(String userName, String password);
    List<User> getAll();
    Optional<User> getUserByID(long id);
    boolean addUser(User user) throws UserServiceException;
    boolean removeUserById(long id);
}
