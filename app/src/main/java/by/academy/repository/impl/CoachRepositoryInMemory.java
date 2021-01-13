package by.academy.repository.impl;

import by.academy.model.bean.Coach;
import by.academy.repository.ICoachRepository;
import by.academy.specification.ICoachSpecification;

import java.util.LinkedList;
import java.util.List;

public class CoachRepositoryInMemory implements ICoachRepository {

    private static final List<Coach> coaches;

    static {
        coaches = new LinkedList<>();
    }

    @Override
    public void addSalary(Coach coach, int salary) {
        synchronized (UserRepositoryInMemory.class) {
            boolean isExist = false;
            for (Coach entryCoach: coaches) {
                if(entryCoach.equals(coach)) {
                    entryCoach.addSalary(salary);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                coach.addSalary(salary);
                coaches.add(coach);
            }
        }
    }

    @Override
    public List<Coach> query(ICoachSpecification specification) {
        List<Coach> coachList = new LinkedList<>();
        synchronized (UserRepositoryInMemory.class) {
            for(Coach coach: coaches) {
                if(specification.specification(coach)) {
                    coachList.add(coach);
                }
            }
        }
        return coachList;
    }
}
