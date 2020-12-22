package by.academy.service;

import by.academy.model.bean.User;

public interface IUserService {
    User userLogin(String userName, String password);
    boolean addUser(User user);
}
