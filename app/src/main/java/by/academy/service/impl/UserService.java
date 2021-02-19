package by.academy.service.impl;

import by.academy.exception.AppException;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.repository.impl.UserRepositoryHibernate;
import by.academy.service.IUserService;
import by.academy.specification.impl.user.AllUsersSpecification;
import by.academy.specification.impl.user.IdSpecification;
import by.academy.specification.impl.user.UserNameAndPasswordSpecification;
import by.academy.specification.impl.user.UserTypeSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public Optional<User> getUserByUserNameAndPassword(String userName, String password) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryHibernate();
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
            IRepository<User> repository = new UserRepositoryHibernate();
            if (null != user) {
                optional = repository.add(user);
            }
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return optional;
    }

    @Override
    public Optional<User> removeUserById(long id) {
        Optional<User> optional = Optional.empty();
        try {
            IRepository<User> repository = new UserRepositoryHibernate();
            Optional<User> user = getUserByID(id);
            if(user.isPresent()) {
                optional = repository.remove(user.get());
            }
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return optional;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryHibernate();
            userList = repository.query(new AllUsersSpecification());
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList;
    }

    @Override
    public List<User> getAll(UserType userType) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryHibernate();
            userList = repository.query(new UserTypeSpecification(userType));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList;
    }

    @Override
    public Optional<User> getUserByID(long id) {
        List<User> userList = new LinkedList<>();
        try {
            IRepository<User> repository = new UserRepositoryHibernate();
            userList = repository.query(new IdSpecification(id));
        } catch (AppException e) {
            log.error(e.getMessage());
        }
        return userList.stream().findFirst();
    }
}
