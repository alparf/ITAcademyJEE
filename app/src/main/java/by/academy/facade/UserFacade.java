package by.academy.facade;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.service.ICoachService;
import by.academy.service.IUserService;
import by.academy.service.impl.CoachService;
import by.academy.service.impl.UserService;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFacade {

    public static void addSalary(long coachId, Integer salary) {
        ICoachService coachService = new CoachService();
        coachService.addSalary(coachId, salary);
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
        password = Base64.getEncoder().encodeToString(password.getBytes());
        return service.login(userName, password);
    }

    public static List<User> getAllUsers() {
        IUserService service = new UserService();
        return service.getAll();
    }

    public static void addUser(User user) {
        IUserService service = new UserService();
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        service.addUser(user);
    }

    public static void removeUserById(long id) {
        IUserService service = new UserService();
        service.removeUserById(id);
    }
}
