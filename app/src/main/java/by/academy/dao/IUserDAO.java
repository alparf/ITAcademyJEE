package by.academy.dao;

import by.academy.model.bean.User;

import java.util.Map;

public interface IUserDAO {
    User getUser(String userName);
    Map<String, User> getUsers();
    boolean addUser(User user);
    boolean removeUser(String userName);
}
