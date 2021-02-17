package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;

import java.util.List;

public class UserRepositoryHibernate implements IRepository<User> {

    @Override
    public boolean add(User user) {

        return false;
    }

    @Override
    public boolean remove(User user) {
        return false;
    }

    @Override
    public boolean set(User user) {
        return false;
    }

    @Override
    public List<User> query(ISpecification<User> specification) {
        return null;
    }
}
