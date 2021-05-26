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
    private static volatile GroupService service;
    private final IRepository<Group> repository = new GroupHibernateRepository();

    public static GroupService getService() {
        if (null == service) {
            synchronized (GroupService.class) {
                if (null == service) {
                    service = new GroupService();
                }
            }
        }
        return service;
    }

    @Override
    public Optional<Group> addGroup(Group group) {
        return this.repository.add(group);
    }

    @Override
    public Optional<Group> setGroup(Group group) {
        return this.repository.set(group);
    }

    @Override
    public Optional<Group> removeGroup(long groupId) {
        return this.repository.remove(this.repository.query(new GroupIdSpecification(groupId)).stream()
                .findFirst()
                .get());
    }

    @Override
    public Optional<Group> findGroup(long groupId) {
        return this.repository.query(new GroupIdSpecification(groupId)).stream()
                .findFirst();
    }

    @Override
    public List<Group> findAllGroups() {
        return this.repository.query(new AllGroupSpecification());
    }

    private GroupService() {

    }
}
