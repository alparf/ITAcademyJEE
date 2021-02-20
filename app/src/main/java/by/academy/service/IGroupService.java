package by.academy.service;

import by.academy.model.bean.Group;

import java.util.List;
import java.util.Optional;

public interface IGroupService {
    Optional<Group> newGroup(Group group);
    Optional<Group> remove(long groupId);
    List<Group> getAll();
}
