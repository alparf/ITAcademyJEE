package by.academy.service;

import by.academy.model.bean.Group;

import java.util.List;
import java.util.Optional;

public interface IGroupService {
    Optional<Group> addGroup(Group group);
    Optional<Group> setGroup(Group group);
    Optional<Group> removeGroup(long groupId);
    Optional<Group> findGroup(long groupId);
    List<Group> findAllGroups();
}
