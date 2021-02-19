package by.academy.repository.impl;

import by.academy.model.bean.Group;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;

import java.util.List;
import java.util.Optional;

public class GroupHibernateRepository implements IRepository<Group> {
    @Override
    public Optional<Group> add(Group group) {
        return Optional.empty();
    }

    @Override
    public Optional<Group> remove(Group group) {
        return Optional.empty();
    }

    @Override
    public Optional<Group> set(Group group) {
        return Optional.empty();
    }

    @Override
    public List<Group> query(ISpecification<Group> specification) {
        return null;
    }
}
