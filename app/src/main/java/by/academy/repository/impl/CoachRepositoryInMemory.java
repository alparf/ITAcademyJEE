package by.academy.repository.impl;

import by.academy.model.bean.Coach;
import by.academy.repository.ICoachRepository;
import by.academy.specification.ICoachSpecification;

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
    public void addCoach(Coach coach) {
        synchronized (CoachRepositoryInMemory.class) {
            if (null != coach) {
                coach.setId(nextId);
                nextId++;
                coaches.put(coach.getId(), coach);
            }
        }
    }

    @Override
    public void removeCoach(Coach coach) {
        synchronized (CoachRepositoryInMemory.class) {
            if (null != coach) {
                coaches.remove(coach.getId());
            }
        }
    }

    @Override
    public void setCoach(Coach coach) {
        synchronized (CoachRepositoryInMemory.class) {
            if (null != coach) {
                coaches.put(coach.getId(), coach);
            }
        }
    }

    @Override
    public void addSalary(Coach coach, int salary) {
        synchronized (CoachRepositoryInMemory.class) {
            if (null != coach) {
                if(coaches.containsKey(coach.getId())) {
                    coaches.get(coach.getId()).addSalary(salary);
                } else {
                    coach.addSalary(salary);
                    coaches.put(coach.getId(), coach);
                }
            }
        }
    }

    @Override
    public List<Coach> query(ICoachSpecification specification) {
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
