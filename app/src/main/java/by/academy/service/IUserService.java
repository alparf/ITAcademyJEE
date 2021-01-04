package by.academy.service;

import by.academy.model.bean.User;

import java.util.List;

public interface IUserService {
    User userLogin(String userName, String password);
    List<User> getAll();
    User getUser(String userName);
    boolean addUser(User user);
    boolean removeUser(String userName);
}
