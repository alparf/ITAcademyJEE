package by.academy.service.impl;

import by.academy.dao.IUserDAO;
import by.academy.dao.impl.UserInMemory;
import by.academy.model.bean.User;
import by.academy.model.factory.UserFactory;
import by.academy.service.IUserService;

import java.util.LinkedList;
import java.util.List;

public class UserService implements IUserService {
    public User userLogin(String userName, String password) {
        IUserDAO userDAO = new UserInMemory();
        User user = userDAO.getUser(userName);
        if((null != user) && (UserFactory.PASS_AUTH.authenticate(password.toCharArray(), user.getPassword()))) {
            return user;
        } else {
            return UserFactory.createUser();
        }
    }

    @Override
    public boolean addUser(User user) {
        IUserDAO userDAO = new UserInMemory();
        return userDAO.addUser(user);
    }

    @Override
    public boolean removeUser(String userName) {
        IUserDAO userDAO = new UserInMemory();
        return userDAO.removeUser(userName);
    }

    @Override
    public List<User> getAll() {
        IUserDAO userDAO = new UserInMemory();
        return new LinkedList<>(userDAO.getUsers().values());
    }

    @Override
    public User getUser(String userName) {
        IUserDAO userDAO = new UserInMemory();
        return userDAO.getUser(userName);
    }
}
