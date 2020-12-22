package by.academy.dao;

import by.academy.model.bean.User;

public interface IUserDAO {
    User getUser(String userName, String password);
    boolean addUser(User user);
    boolean removeUser(User user);
}
