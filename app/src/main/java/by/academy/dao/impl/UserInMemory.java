package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.dao.IUserDAO;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;

import java.util.*;

public class UserInMemory implements IUserDAO {

    private static final List<User> users = new LinkedList<>();

    static {
        users.add(UserFactory.createUser(
                "Иванов Иван Иванович", 19, "student", "student", UserType.STUDENT));
        users.add(UserFactory.createUser(
                "Петров Петр Петрович", 44, "coach", "coach", UserType.COACH));
    }

    /**
     *
     * @param userName User name
     * @param password User password
     * @return User or throw UserNotFoundException
     * @throws UserServiceException
     * looking for a user with an equivalent userName and password and return it.
     * If user is not found throw UserNotFoundException.
     */

    @Override
    public User getUser(String userName, String password) {
        Optional<User> optionalUser;
        synchronized (UserInMemory.class) {
            optionalUser = users.stream()
                    .filter(user -> userFilter(user, userName, password))
                    .findFirst();
        }
        return optionalUser.isPresent() ? optionalUser.get() : UserFactory.createUser();
    }

    /**
     *
     * @param userName User name
     * @return First User or throw UserServiceException
     */

    @Override
    public User getUser(String userName) {
        Optional<User> optionalUser;
        synchronized (UserInMemory.class) {
            optionalUser = users.stream()
                    .filter(user -> userName.equals(user.getUserName()))
                    .findFirst();
        }
        return optionalUser.isPresent() ? optionalUser.get() : UserFactory.createUser();
    }

    /**
     *
     * @param user User name
     * @return add User to UserInMemory or throw UserServiceException if User.userName already used
     */

    @Override
    public boolean addUser(User user) {
        if(null != user) {
            synchronized (UserInMemory.class) {
                if(!isUserNameUsed(user.getUserName())) {
                    users.add(user);
                } else {
                    throw new UserServiceException(ExceptionConstant.USER_NAME_ALREADY_USED);
                }
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @param userName User name
     * @return Remove the first User named userName
     */

    @Override
    public boolean removeUser(String userName) {
        if(null != userName) {
            synchronized (UserInMemory.class) {
                users.stream()
                        .filter(user -> userName.equals(user.getUserName()))
                        .findFirst()
                        .ifPresent(users::remove);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsers() {
        return new LinkedList<>(users);
    }

    private boolean userFilter(User user, String userName, String password) {
        if(null == user) {
            return false;
        }
        return (user.getUserName().equals(userName)
                && (UserFactory.PASS_AUTH.authenticate(password.toCharArray(), user.getPassword())));
    }

    private boolean isUserNameUsed(String userName) {
        Optional<String> result = users.stream()
                .map(User::getUserName)
                .filter(userName::equals)
                .findFirst();
        return result.isPresent();
    }
}
