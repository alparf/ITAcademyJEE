package by.academy.facade;

import by.academy.model.bean.Group;
import by.academy.service.IGroupService;
import by.academy.service.impl.GroupService;

import java.util.List;

public class GroupFacade {
    public static List<Group> getAllGroups() {
        IGroupService service = new GroupService();
        return service.getAll();
    }
}
