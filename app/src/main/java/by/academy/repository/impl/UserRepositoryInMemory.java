package by.academy.repository.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;

import java.util.*;

public class UserRepositoryInMemory implements IRepository<User> {

    private static final Map<Long, User> users;
    private static volatile long nextId;

    static {
        nextId = 1;
        users = new HashMap<>();

        IRepository<User> repository = new UserRepositoryInMemory();
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
    public List<User> query(ISpecification<User> specification) {
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
