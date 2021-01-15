package by.academy.repository.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
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
        repository.addUser(User.newBuilder()
                        .withFio("Иванов Иван Иванович")
                        .withAge(19)
                        .withUserName("student")
                        .withPassword("student")
                        .withUserType(UserType.STUDENT)
                        .build());
        repository.addUser(User.newBuilder()
                        .withFio("Петров Петр Петрович")
                        .withAge(44)
                        .withUserName("coach")
                        .withPassword("coach")
                        .withUserType(UserType.COACH)
                        .build());
    }

    @Override
    public boolean addUser(User user) {
        synchronized (UserRepositoryInMemory.class) {
            if (null != user) {
                user.setId(nextId);
                nextId++;
                users.put(user.getId(), user);
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean removeUser(User user) {
        synchronized (UserRepositoryInMemory.class) {
            if (null != user) {
                users.remove(user.getId());
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean setUser(User user) {
        synchronized (UserRepositoryInMemory.class) {
            if (null != user) {
                if(users.containsKey(user.getId())) {
                    users.put(user.getId(), user);
                    return true;
                }
            }
            return false;
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
