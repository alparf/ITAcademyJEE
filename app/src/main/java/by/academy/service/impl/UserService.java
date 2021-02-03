package by.academy.service.impl;

import by.academy.constant.ExceptionMessage;
import by.academy.exception.AppException;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.IUserService;
import by.academy.specification.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public Optional<User> getUserByUserNameAndPassword(String userName, String password) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryDB();
            userList = repository.query(new UserLoginSpecification(userName, password));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList.stream().findFirst();
    }

    @Override
    public boolean addUser(User user) throws UserServiceException {
        try {
            IRepository<User> repository = new UserRepositoryDB();
            if (null != user) {
                if (!isUserNameUsed(user.getUserName())) {
                    return repository.add(user);
                } else {
                    throw new UserServiceException(ExceptionMessage.USER_NAME_ALREADY_USED);
                }
            }
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeUserById(long id) {
        boolean success = false;
        try {
            IRepository<User> repository = new UserRepositoryDB();
            Optional<User> user = getUserByID(id);
            if(user.isPresent()) {
                success = repository.remove(user.get());
            }
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return success;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryDB();
            userList = repository.query(new UsersSpecification());
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList;
    }

    @Override
    public List<User> getAll(UserType userType) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryDB();
            userList = repository.query(new UsersByUserTypeSpecification(userType));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList;
    }

    @Override
    public Optional<User> getUserByID(long id) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryDB();
            userList = repository.query(new UserByIdSpecification(id));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList.stream().findFirst();
    }

    private boolean isUserNameUsed(String userName) {
        List<User> userList = new ArrayList<>();
        try {
            IRepository<User> repository = new UserRepositoryDB();
            userList = repository.query(new UserByUserNameSpecification(userName));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return !userList.isEmpty();
    }
}
