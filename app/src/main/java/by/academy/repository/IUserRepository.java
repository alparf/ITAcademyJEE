package by.academy.repository;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

import java.util.List;

public interface IUserRepository {
    void addUser(User user);
    void removeUser(User user);
    void setUser(User user);
    List<User> query(IUserSpecification specification);
}
