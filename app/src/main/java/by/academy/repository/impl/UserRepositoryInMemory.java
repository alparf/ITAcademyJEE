package by.academy.repository.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import by.academy.repository.IUserRepository;
import by.academy.specification.IUserSpecification;

import java.util.*;

public class UserRepositoryInMemory implements IUserRepository {

    private static final Map<Long, User> users;
    private static volatile long nextId;

    static {
        nextId = 1;
        users = new HashMap<>();

        IUserRepository repository = new UserRepositoryInMemory();
        repository.addUser(UserFactory.createUser(
                0, "Иванов Иван Иванович", 19, "student", "student", UserType.STUDENT));
        repository.addUser(UserFactory.createUser(
                0, "Петров Петр Петрович", 44, "coach", "coach", UserType.COACH));
    }

    @Override
    public void addUser(User user) {
        synchronized (UserRepositoryInMemory.class) {
            if (null != user) {
                user.setId(nextId);
                nextId++;
                users.put(user.getId(), user);
            }
        }
    }

    @Override
    public void removeUser(User user) {
        synchronized (UserRepositoryInMemory.class) {
            if (null != user) {
                users.remove(user.getId());
            }
        }
    }

    @Override
    public void setUser(User user) {
        synchronized (UserRepositoryInMemory.class) {
            if (null != user) {
                if(users.containsKey(user.getId())) {
                    users.put(user.getId(), user);
                }
            }
        }
    }

    @Override
    public List<User> query(IUserSpecification specification) {
        List<User> userList = new LinkedList<>();
        synchronized (UserRepositoryInMemory.class) {
            for (User user : users.values()) {
                if (specification.specification(user)) {
                    userList.add(user);
                }
            }
        }
        return userList;
    }
}
