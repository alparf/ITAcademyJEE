package by.academy.repository.impl;

import by.academy.model.bean.Coach;
import by.academy.repository.ICoachRepository;
import by.academy.specification.ISpecification;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CoachRepositoryInMemory implements ICoachRepository {

    private static final Map<Long, Coach> coaches;
    private static volatile long nextId;

    static {
        coaches = new HashMap<>();
        nextId = 1;
    }

    @Override
    public boolean addSalary(Coach coach, int salary) {
        synchronized (CoachRepositoryInMemory.class) {
            if (null != coach) {
                if (coaches.containsKey(coach.getUser().getId())) {
                    coaches.get(coach.getUser().getId()).addSalary(salary);
                    return true;
                } else {
                    coach.addSalary(salary);
                    coaches.put(coach.getUser().getId(), coach);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public List<Coach> query(ISpecification<Coach> specification) {
        List<Coach> coachList = new LinkedList<>();
        synchronized (UserRepositoryInMemory.class) {
            for(Coach coach: coaches.values()) {
                if(specification.specification(coach)) {
                    coachList.add(coach);
                }
            }
        }
        return coachList;
    }
}
