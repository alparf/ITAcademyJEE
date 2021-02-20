package by.academy.facade;

import by.academy.model.bean.Group;
import by.academy.service.IGroupService;
import by.academy.service.IUserService;
import by.academy.service.impl.GroupService;
import by.academy.service.impl.UserService;

import java.util.List;
import java.util.Optional;

public class GroupFacade {
    public static List<Group> getAll() {
        IGroupService groupService = new GroupService();
        return groupService.getAll();
    }

    public static Optional<Group> newGroup(String groupName, Long coachId) {
        IGroupService groupService = new GroupService();
        IUserService userService = new UserService();
        Group group = Group.getBuilder()
                .withName(groupName)
                .withCoach(userService.getUser(coachId).get())
                .build();
        return groupService.newGroup(group);
    }

    public static Optional<Group> remove(Long groupId) {
        IGroupService groupService = new GroupService();
        return groupService.remove(groupId);
    }
}
