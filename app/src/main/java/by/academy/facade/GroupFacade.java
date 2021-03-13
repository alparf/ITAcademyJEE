package by.academy.facade;

import by.academy.model.bean.Group;
import by.academy.model.bean.User;
import by.academy.service.impl.GroupService;
import by.academy.service.impl.UserService;

import java.util.List;
import java.util.Optional;

public class GroupFacade {
    public static List<Group> getAll() {
        return GroupService.getService().findAllGroups();
    }

    public static Optional<Group> newGroup(String groupName, long coachId) {
        Group group = Group.getBuilder()
                .withName(groupName)
                .withCoach(UserService.getService().findUser(coachId).get())
                .build();
        return GroupService.getService().addGroup(group);
    }

    public static Optional<Group> remove(long groupId) {
        return GroupService.getService().removeGroup(groupId);
    }

    public static Optional<Group> setGroup(long groupId, String groupName, long coachId) {
        Optional<Group> group = GroupService.getService().findGroup(groupId);
        Optional<User> coach = UserService.getService().findUser(coachId);
        if (group.isPresent() && coach.isPresent()) {
            group.get().setName(groupName);
            group.get().setCoach(coach.get());
            group = GroupService.getService().setGroup(group.get());
        }
        return group;
    }
}
