package by.academy.dao.impl;

import by.academy.dao.ICoachDAO;
import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.*;

public class CoachInMemory implements ICoachDAO {

    private static final Map<String, Coach> coaches = new HashMap<>();

    @Override
    public boolean addSalary(Coach coach, Integer salary) {
        if(null != coach) {
            synchronized (CoachInMemory.class) {
                if((null != coach) && (null != coach.getUser())) {
                    String coachName = coach.getUser().getUserName();
                    if (coaches.containsKey(coachName)) {
                        coaches.get(coachName).addSalary(salary);
                    } else {
                        coach.addSalary(salary);
                        coaches.put(coachName, coach);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Coach getCoach(User user) {
        if(null != user) {
            synchronized (CoachInMemory.class) {
                if(null != user) {
                    return coaches.get(user.getUserName());
                }
            }
        }
        return null;
    }

    @Override
    public Map<String, Coach> getCoachList() {
        return new HashMap<>(coaches);
    }
}
