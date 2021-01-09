package by.academy.service.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.factory.UserFactory;
import by.academy.repository.IUserRepository;
import by.academy.repository.impl.UserRepositoryInMemory;
import by.academy.service.IUserService;
import by.academy.specification.impl.UserSpecificationGetAll;
import by.academy.specification.impl.UserSpecificationLogin;
import by.academy.specification.impl.UserSpecificationUserByUserName;

import java.util.List;

public class UserService implements IUserService {

    private static final int USER = 0;

    public User login(String userName, String password) {
        IUserRepository repository = new UserRepositoryInMemory();
        List<User> userList = repository.query(new UserSpecificationLogin(userName, password));
        if(!userList.isEmpty()) {
            return userList.get(USER);
        }
        return null;
    }

    @Override
    public void addUser(User user) throws UserServiceException {
        IUserRepository repository = new UserRepositoryInMemory();
        if (null != user) {
            if (!isUserNameUsed(user.getUserName())) {
                repository.addUser(user);
            } else {
                throw new UserServiceException(ExceptionConstant.USER_NAME_ALREADY_USED);
            }
        }
    }

    @Override
    public void removeUser(String userName) {
        IUserRepository repository = new UserRepositoryInMemory();
        List<User> userList = repository.query(new UserSpecificationUserByUserName(userName));
        if(!userList.isEmpty()) {
            repository.removeUser(userList.get(USER));
        }
    }

    @Override
    public List<User> getAll() {
        IUserRepository repository = new UserRepositoryInMemory();
        return repository.query(new UserSpecificationGetAll());
    }

    @Override
    public User getUserByName(String userName) {
        IUserRepository repository = new UserRepositoryInMemory();
        List<User> userList = repository.query(new UserSpecificationUserByUserName(userName));
        if(!userList.isEmpty()) {
            return userList.get(USER);
        }
        return UserFactory.createUser();
    }

    private boolean isUserNameUsed(String userName) {
        IUserRepository repository = new UserRepositoryInMemory();
        List<User> userList = repository.query(new UserSpecificationUserByUserName(userName));
        return !userList.isEmpty();
    }
}
