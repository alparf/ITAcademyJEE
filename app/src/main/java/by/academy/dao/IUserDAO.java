package by.academy.dao;

import by.academy.model.bean.User;

import java.util.List;

public interface IUserDAO {
    User getUser(String userName, String password);
    List<User> getUsers();
    boolean addUser(User user);
    boolean removeUser(String userName);
}
