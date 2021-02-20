package by.academy.service.impl;

import by.academy.constant.ExceptionMessage;
import by.academy.exception.AppException;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.repository.impl.UserHibernateRepository;
import by.academy.service.IUserService;
import by.academy.specification.impl.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public Optional<User> findUser(String userName, String password) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserHibernateRepository();
            userList = repository.query(new UserNameAndPasswordSpecification(userName, password));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList.stream().findFirst();
    }

    @Override
    public Optional<User> addUser(User user) throws UserServiceException {
        Optional<User> optional = Optional.empty();
        try {
            IRepository<User> repository = new UserHibernateRepository();
            if (null != user) {
                if (repository.query(new UserNameSpecification(user.getUserName())).isEmpty()) {
                    optional = repository.add(user);
                } else {
                    throw new UserServiceException(ExceptionMessage.USER_NAME_ALREADY_USED);
                }
            }
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return optional;
    }

    @Override
    public Optional<User> removeUser(long id) {
        Optional<User> optional = Optional.empty();
        try {
            IRepository<User> repository = new UserHibernateRepository();
            Optional<User> user = findUser(id);
            if(user.isPresent()) {
                optional = repository.remove(user.get());
            }
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return optional;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserHibernateRepository();
            userList = repository.query(new AllUsersSpecification());
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList;
    }

    @Override
    public List<User> findAllUsers(UserType userType) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserHibernateRepository();
            userList = repository.query(new UserTypeSpecification(userType));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList;
    }

    @Override
    public Optional<User> findUser(long id) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserHibernateRepository();
            userList = repository.query(new UserIdSpecification(id));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList.stream().findFirst();
    }
}
