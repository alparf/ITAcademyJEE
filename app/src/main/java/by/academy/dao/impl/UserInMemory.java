package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.dao.IUserDAO;
import by.academy.exception.UserNotFoundException;
import by.academy.model.bean.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserInMemory implements IUserDAO {

    private static volatile List<User> users;

    private static List<User> getUsers() {
        List<User> localUser = users;
        if(localUser == null) {
            synchronized (UserInMemory.class) {
                localUser = users;
                if(localUser == null) {
                    users = localUser = new LinkedList<>();
                }
            }
        }
        return localUser;
    }

    @Override
    public User getUser(String userName, String password) throws UserNotFoundException {
        getUsers();
        Optional<User> optionalUser = users.stream().filter(user -> {
            if(null == user) {
                return false;
            } else {
                return (user.getUserName().equals(userName) && user.getPassword().equals(password));
            }
        }).findFirst();
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(ExceptionConstant.USER_NOT_FOUND);
        }
        return optionalUser.get();
    }

    @Override
    public boolean addUser(User user) {
        getUsers();
        if(null != user) {
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        getUsers();
        if(null != user) {
            users.remove(user);
        }
        return false;
    }

}
