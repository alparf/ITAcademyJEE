package by.academy.facade;

import by.academy.model.bean.Group;
import by.academy.model.bean.User;
import by.academy.service.IGroupService;
import by.academy.service.IUserService;
import by.academy.service.impl.GroupService;
import by.academy.service.impl.UserService;

import java.util.List;
import java.util.Optional;

public class GroupFacade {
    public static List<Group> getAll() {
        IGroupService groupService = new GroupService();
        return groupService.findAllGroups();
    }

    public static Optional<Group> newGroup(String groupName, long coachId) {
        IGroupService groupService = new GroupService();
        IUserService userService = new UserService();
        Group group = Group.getBuilder()
                .withName(groupName)
                .withCoach(userService.findUser(coachId).get())
                .build();
        return groupService.addGroup(group);
    }

    public static Optional<Group> remove(long groupId) {
        IGroupService groupService = new GroupService();
        return groupService.removeGroup(groupId);
    }

    public static Optional<Group> setGroup(long groupId, String groupName, long coachId) {
        IGroupService groupService = new GroupService();
        IUserService userService = new UserService();
        Optional<Group> group = groupService.findGroup(groupId);
        Optional<User> coach = userService.findUser(coachId);
        if (group.isPresent() && coach.isPresent()) {
            group.get().setName(groupName);
            group.get().setCoach(coach.get());
            group = groupService.setGroup(group.get());
        }
        return group;
    }
}
