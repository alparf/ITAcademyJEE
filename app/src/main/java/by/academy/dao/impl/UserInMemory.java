package by.academy.dao.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.dao.IUserDAO;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;

import java.util.*;

public class UserInMemory implements IUserDAO {

    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("student", UserFactory.createUser(
                "Иванов Иван Иванович", 19, "student", "student", UserType.STUDENT));
        users.put("coach", UserFactory.createUser(
                "Петров Петр Петрович", 44, "coach", "coach", UserType.COACH));
    }

    @Override
    public User getUser(String userName) {
        synchronized (UserInMemory.class) {
            return users.get(userName);
        }
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
                if(!users.containsKey(user.getUserName())) {
                    users.put(user.getUserName(), user);
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
                users.remove(userName);
            }
        }
        return false;
    }

    @Override
    public Map<String, User> getUsers() {
        return new HashMap<>(users);
    }
}
