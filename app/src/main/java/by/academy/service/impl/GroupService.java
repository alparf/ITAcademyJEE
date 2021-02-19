package by.academy.service.impl;

import by.academy.model.bean.Group;
import by.academy.repository.IRepository;
import by.academy.repository.impl.GroupHibernateRepository;
import by.academy.service.IGroupService;
import by.academy.specification.impl.group.AllGroupSpecification;

import java.util.List;
import java.util.Optional;

public class GroupService implements IGroupService {
    @Override
    public Optional<Group> addGroup(Group group) {
        IRepository<Group> repository = new GroupHibernateRepository();
        return repository.add(group);
    }

    @Override
    public List<Group> getAll() {
        IRepository<Group> repository = new GroupHibernateRepository();
        return repository.query(new AllGroupSpecification());
    }
}
