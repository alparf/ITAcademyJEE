package by.academy.repository;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

import java.util.List;

public interface IUserRepository {
    boolean addUser(User user);
    boolean removeUser(User user);
    boolean setUser(User user);
    List<User> query(IUserSpecification specification);
}
