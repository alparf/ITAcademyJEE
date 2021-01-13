package by.academy.service.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.repository.IUserRepository;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.IUserService;
import by.academy.specification.UserDBSpecifications;

import java.util.List;

public class UserService implements IUserService {

    private static final int USER = 0;

    public User login(String userName, String password) {
        IUserRepository repository = new UserRepositoryDB();
        List<User> userList = repository.query(UserDBSpecifications.userLogin(userName, password));
        if(!userList.isEmpty()) {
            return userList.get(USER);
        }
        return null;
    }

    @Override
    public void addUser(User user) throws UserServiceException {
        IUserRepository repository = new UserRepositoryDB();
        if (null != user) {
            if (!isUserNameUsed(user.getUserName())) {
                repository.addUser(user);
            } else {
                throw new UserServiceException(ExceptionConstant.USER_NAME_ALREADY_USED);
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        IUserRepository repository = new UserRepositoryDB();
        repository.removeUser(getUserByID(id));
    }

    @Override
    public List<User> getAll() {
        IUserRepository repository = new UserRepositoryDB();
        return repository.query(UserDBSpecifications.allUsers());
    }

    @Override
    public User getUserByID(long id) {
        IUserRepository repository = new UserRepositoryDB();
        List<User> userList = repository.query(UserDBSpecifications.userById(id));
        if(!userList.isEmpty()) {
            return userList.get(USER);
        }
        return null;
    }

    private boolean isUserNameUsed(String userName) {
        IUserRepository repository = new UserRepositoryDB();
        List<User> userList = repository.query(UserDBSpecifications.userByUserName(userName));
        return !userList.isEmpty();
    }
}
