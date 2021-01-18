package by.academy.service.impl;

import by.academy.constant.ExceptionConstant;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.repository.IUserRepository;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.IUserService;
import by.academy.specification.UserDBSpecifications;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

    @Override
    public Optional<User> getUserByUserNameAndPassword(String userName, String password) {
        IUserRepository repository = new UserRepositoryDB();
        List<User> userList = repository.query(UserDBSpecifications.userByUserNameAndPassword(userName, password));
        return userList.stream().findFirst();
    }

    @Override
    public boolean addUser(User user) throws UserServiceException {
        IUserRepository repository = new UserRepositoryDB();
        if (null != user) {
            if (!isUserNameUsed(user.getUserName())) {
                return repository.addUser(user);
            } else {
                throw new UserServiceException(ExceptionConstant.USER_NAME_ALREADY_USED);
            }
        }
        return false;
    }

    @Override
    public boolean removeUserById(long id) {
        IUserRepository repository = new UserRepositoryDB();
        return repository.removeUser(getUserByID(id).get());
    }

    @Override
    public List<User> getAll() {
        IUserRepository repository = new UserRepositoryDB();
        return repository.query(UserDBSpecifications.allUsers());
    }

    @Override
    public Optional<User> getUserByID(long id) {
        IUserRepository repository = new UserRepositoryDB();
        List<User> userList = repository.query(UserDBSpecifications.userById(id));
        return userList.stream().findFirst();
    }

    private boolean isUserNameUsed(String userName) {
        IUserRepository repository = new UserRepositoryDB();
        List<User> userList = repository.query(UserDBSpecifications.userByUserName(userName));
        return !userList.isEmpty();
    }
}
