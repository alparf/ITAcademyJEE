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

    public static boolean addSalary(String coachName, Integer salary) {
        ICoachService coachService = new CoachService();
        IUserService userService = new UserService();
        return coachService.addSalary(CoachFactory.createCoach(userService.getUser(coachName)), salary);
    }

    public static List<Coach> getAllCoaches() {
        ICoachService service = new CoachService();
        return service.getAll();
    }

    public static Map<String, Integer> getAverageSalaries(int monthCount) {
        Map<String, Integer> averageSalaries = new HashMap<>();
        List<Coach> coachList = getAllCoaches();
        for(Coach coach: coachList) {
            if((null != coach) && (null != coach.getUser())) {
                averageSalaries.put(coach.getUser().getFio(), coach.getAverageSalary(monthCount));
            }
        }
        return averageSalaries;
    }

    public static User userLogin(String userName, String password) {
        IUserService service = new UserService();
        return service.userLogin(userName, password);
    }

    public static List<User> getAllUsers() {
        IUserService service = new UserService();
        return service.getAll();
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
