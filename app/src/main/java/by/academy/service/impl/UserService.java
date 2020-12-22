package by.academy.service.impl;

import by.academy.dao.IUserDAO;
import by.academy.dao.impl.UserInMemory;
import by.academy.model.bean.User;
import by.academy.service.IUserService;

public class UserService implements IUserService {
    public User userLogin(String userName, String password) {
        IUserDAO userDAO = new UserInMemory();
        return userDAO.getUser(userName, password);
    }

    @Override
    public boolean addUser(User user) {
        IUserDAO userDAO = new UserInMemory();
        return userDAO.addUser(user);
    }

    @Override
    public boolean removeUser(User user) {
        IUserDAO userDAO = new UserInMemory();
        return userDAO.removeUser(user);
    }
}
