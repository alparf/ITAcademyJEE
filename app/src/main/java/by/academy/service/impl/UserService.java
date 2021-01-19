package by.academy.service.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.exception.AppException;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.IUserService;
import by.academy.specification.UserDBSpecifications;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    @Override
    public Optional<User> getUserByUserNameAndPassword(String userName, String password) {
        List<User> userList = new LinkedList<>();
        IRepository<User> repository = new UserRepositoryDB();
        try {
            userList = repository.query(UserDBSpecifications.userByUserNameAndPassword(userName, password));
        } catch (AppException e) {
            e.printStackTrace();
        }
        return userList.stream().findFirst();
    }

    @Override
    public boolean addUser(User user) throws UserServiceException {
        IRepository<User> repository = new UserRepositoryDB();
        try {
            if (null != user) {
                if (!isUserNameUsed(user.getUserName())) {
                    return repository.addUser(user);
                } else {
                    throw new UserServiceException(ExceptionConstant.USER_NAME_ALREADY_USED);
                }
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeUserById(long id) {
        try {
            IRepository<User> repository = new UserRepositoryDB();
            return repository.removeUser(getUserByID(id).get());
        } catch (AppException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new LinkedList<>();
        IRepository<User> repository = new UserRepositoryDB();
        try {
            userList = repository.query(UserDBSpecifications.allUsers());
        } catch (AppException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Optional<User> getUserByID(long id) {
        List<User> userList = new LinkedList<>();
        IRepository<User> repository = new UserRepositoryDB();
        try {
            userList = repository.query(UserDBSpecifications.userById(id));
        } catch (AppException e) {
            e.printStackTrace();
        }
        return userList.stream().findFirst();
    }

    private boolean isUserNameUsed(String userName) {
        IRepository<User> repository = new UserRepositoryDB();
        List<User> userList = new ArrayList<>();
        try {
            userList = repository.query(UserDBSpecifications.userByUserName(userName));
        } catch (AppException e) {
            e.printStackTrace();
        }
        return !userList.isEmpty();
    }
}
