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

public class CoachFacade {
    public static Optional<Salary> addSalary(long coachId, double salaryValue) {
        ISalaryService salaryService = new SalaryService();
        IUserService userService = new UserService();
        User user = userService.findUser(coachId).get();
        Salary salary = Salary.newBuilder()
                .withValue(formatSalary(salaryValue))
                .withCoach(user)
                .build();
        return salaryService.addSalary(salary);
    }

    public static List<Coach> getAll() {
        IUserService userService = new UserService();
        ISalaryService salaryService = new SalaryService();
        List<Coach> coaches = new LinkedList<>();
        userService.findAllUsers(UserType.COACH).forEach(user -> coaches.add(
                Coach.getBuilder()
                        .withUser(user)
                        .withSalaries(new LinkedList<>())
                        .build()));
        Iterator<Coach> iterator = coaches.iterator();
        while (iterator.hasNext()) {
            Coach coach = iterator.next();
            salaryService.findAllSalaries(coach.getUser().getId())
                    .forEach(coach::addSalary);
        }
        return coaches;
    }
    public static Map<String, Integer> getAverageSalaries(int monthCount) {
        Map<String, Integer> averageSalaries = new HashMap<>();
        List<Coach> coaches = getAll();
        for(Coach coach: coaches) {
            if((null != coach) && (null != coach.getUser())) {
                averageSalaries.put(
                        coach.getUser().getFio(),
                        coach.getAverageSalary(monthCount));
            }
        }
        return averageSalaries;
    }

    private static int formatSalary(double salaryValue) {
        return (int) (100 * salaryValue);
    }
}
