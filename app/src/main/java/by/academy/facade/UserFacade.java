package by.academy.facade;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.factory.CoachFactory;
import by.academy.service.ICoachService;
import by.academy.service.IUserService;
import by.academy.service.impl.CoachService;
import by.academy.service.impl.UserService;

import java.util.List;

public class UserFacade {
    public static boolean addSalary(String coachName, Integer salary) {
        ICoachService coachService = new CoachService();
        IUserService userService = new UserService();
        return coachService.addSalary(CoachFactory.createCoach(userService.getUser(coachName)), salary);
    }

    public static Coach getCoach(User user) {
        ICoachService service = new CoachService();
        return service.getCoach(user);
    }

    public static List<Coach> getCoachList() {
        ICoachService service = new CoachService();
        return service.getCoachList();
    }

    public static User userLogin(String userName, String password) {
        IUserService service = new UserService();
        return service.userLogin(userName, password);
    }

    public static User getUser(String userName) {
        IUserService service = new UserService();
        return service.getUser(userName);
    }

    public static List<User> getUsers() {
        IUserService service = new UserService();
        return service.getUsers();
    }

    public static boolean addUser(User user) {
        IUserService service = new UserService();
        return service.addUser(user);
    }

    public static boolean removeUser(String userName) {
        IUserService service = new UserService();
        return service.removeUser(userName);
    }
}
