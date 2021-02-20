package by.academy.service.impl;

import by.academy.model.bean.Group;
import by.academy.repository.IRepository;
import by.academy.repository.impl.GroupHibernateRepository;
import by.academy.service.IGroupService;
import by.academy.specification.impl.group.AllGroupSpecification;
import by.academy.specification.impl.group.GroupIdSpecification;

import java.util.List;
import java.util.Optional;

public class GroupService implements IGroupService {
    @Override
    public Optional<Group> addGroup(Group group) {
        IRepository<Group> groupIRepository = new GroupHibernateRepository();
        return groupIRepository.add(group);
    }

    @Override
    public Optional<Group> setGroup(Group group) {
        IRepository<Group> groupIRepository = new GroupHibernateRepository();
        return groupIRepository.set(group);
    }

    @Override
    public Optional<Group> removeGroup(long groupId) {
        IRepository<Group> groupIRepository = new GroupHibernateRepository();
        return groupIRepository.remove(groupIRepository.query(new GroupIdSpecification(groupId)).stream()
                .findFirst()
                .get());
    }

    @Override
    public Optional<Group> findGroup(long groupId) {
        IRepository<Group> groupIRepository = new GroupHibernateRepository();
        return groupIRepository.query(new GroupIdSpecification(groupId)).stream()
                .findFirst();
    }

    @Override
    public List<Group> findAllGroups() {
        IRepository<Group> groupIRepository = new GroupHibernateRepository();
        return groupIRepository.query(new AllGroupSpecification());
    }
}
