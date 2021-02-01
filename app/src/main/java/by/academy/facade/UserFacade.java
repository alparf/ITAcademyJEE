package by.academy.facade;

import by.academy.model.bean.Coach;
import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.service.ISalaryService;
import by.academy.service.IUserService;
import by.academy.service.impl.SalaryService;
import by.academy.service.impl.UserService;

import java.util.*;

public class UserFacade {

    public static boolean addSalary(long coachId, Integer salaryValue) {
        ISalaryService salaryService = new SalaryService();
        IUserService userService = new UserService();
        User user = userService.getUserByID(coachId).get();
        Salary salary = Salary.newBuilder()
                .withValue(salaryValue)
                .withCoach(user)
                .build();
        return salaryService.addSalary(salary);
    }

    public static Map<String, Integer> getAverageSalaries(int monthCount) {
        Map<String, Integer> averageSalaries = new HashMap<>();
        List<Coach> coaches = getAllCoaches();
        for(Coach coach: coaches) {
            if((null != coach) && (null != coach.getUser())) {
                averageSalaries.put(
                        coach.getUser().getFio(),
                        coach.getAverageSalary(monthCount));
            }
        }
        return averageSalaries;
    }

    public static Optional<User> login(String userName, String password) {
        IUserService service = new UserService();
        password = Base64.getEncoder().encodeToString(password.getBytes());
        return service.getUserByUserNameAndPassword(userName, password);
    }

    public static List<User> getAllUsers() {
        IUserService service = new UserService();
        return service.getAll();
    }

    public static List<User> getAllUsers(UserType userType) {
        IUserService service = new UserService();
        return service.getAll(userType);
    }

    public static boolean addUser(User user) {
        IUserService service = new UserService();
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        return service.addUser(user);
    }

    public static boolean removeUserById(long id) {
        IUserService service = new UserService();
        return service.removeUserById(id);
    }

    private static List<Coach> getAllCoaches() {
        IUserService userService = new UserService();
        ISalaryService salaryService = new SalaryService();
        List<Coach> coaches = new LinkedList<>();
        userService.getAll(UserType.COACH).forEach(user -> coaches.add(
                Coach.newBuilder()
                        .withUser(user)
                        .withSalaries(new LinkedList<>())
                        .build()));
        coaches.forEach(coach -> salaryService.getAllByCoachId(
                coach.getUser().getId()).forEach(coach::addSalary)
        );
        return coaches;
    }
}
