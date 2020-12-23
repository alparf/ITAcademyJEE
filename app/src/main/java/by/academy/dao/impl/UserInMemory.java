package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.dao.IUserDAO;
import by.academy.exception.UserNotFoundException;
import by.academy.model.bean.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserInMemory implements IUserDAO {

    private static final List<User> users = new LinkedList<>();

    /**
     *
     * @param userName
     * @param password
     * @return
     * @throws UserNotFoundException
     * looking for a user with an equivalent userName and password and return it.
     * If user is not found throw UserNotFoundException.
     */
    @Override
    public User getUser(String userName, String password) throws UserNotFoundException {
        Optional<User> optionalUser = users.stream()
                .filter(user -> userFilter(user, userName, password))
                .findFirst();
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(ExceptionConstant.USER_NOT_FOUND);
        }
        return optionalUser.get();
    }

    @Override
    public boolean addUser(User user) {
        if(null != user) {
            synchronized (UserInMemory.class) {
                users.add(user);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        if(null != user) {
            synchronized (UserInMemory.class) {
                users.remove(user);
            }
            return true;
        }
        return false;
    }

    private boolean userFilter(User user, String userName, String password) {
        if(null == user) {
            return false;
        }
        return (user.getUserName().equals(userName) && user.getPassword().equals(password));
    }
}
