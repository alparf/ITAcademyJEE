package by.academy.facade;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.factory.CoachFactory;
import by.academy.service.ICoachService;
import by.academy.service.IUserService;
import by.academy.service.impl.CoachService;
import by.academy.service.impl.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFacade {

    public static void addSalary(String coachName, Integer salary) {
        ICoachService coachService = new CoachService();
        IUserService userService = new UserService();
        User user = userService.getUserByName(coachName);
        coachService.addSalary(CoachFactory.createCoach(user.getId(), user), salary);
    }

    public static List<Coach> getAllCoaches() {
        ICoachService service = new CoachService();
        return service.getAll();
    }

    public static Map<String, Integer> getAverageSalaries(int monthCount) {
        Map<String, Integer> averageSalaries = new HashMap<>();
        List<Coach> coaches = getAllCoaches();
        for(Coach coach: coaches) {
            if((null != coach) && (null != coach.getUser())) {
                averageSalaries.put(coach.getUser().getFio(), coach.getAverageSalary(monthCount));
            }
        }
        return averageSalaries;
    }

    public static User login(String userName, String password) {
        IUserService service = new UserService();
        return service.login(userName, password);
    }

    public static List<User> getAllUsers() {
        IUserService service = new UserService();
        return service.getAll();
    }

    public static void addUser(User user) {
        IUserService service = new UserService();
        service.addUser(user);
    }

    public static void removeUser(String userName) {
        IUserService service = new UserService();
        service.removeUser(userName);
    }
}
