package by.academy.dao;

import by.academy.model.bean.User;

import java.util.List;

public interface IUserDAO {
    User getUser(String userName, String password);
    User getUser(String userName);
    List<User> getUsers();
    boolean addUser(User user);
    boolean removeUser(String userName);
}
