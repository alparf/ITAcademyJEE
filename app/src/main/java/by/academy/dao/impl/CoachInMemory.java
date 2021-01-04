package by.academy.dao.impl;

import by.academy.dao.ICoachDAO;
import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CoachInMemory implements ICoachDAO {

    private static final List<Coach> coaches = new LinkedList<>();

    @Override
    public boolean addSalary(Coach coach, Integer salary) {
        if(null != coach) {
            synchronized (CoachInMemory.class) {
                Optional<Coach> optional = coaches.stream()
                        .filter(coach::equals)
                        .findFirst();
                if (optional.isPresent()) {
                    optional.get().addSalary(salary);
                } else {
                    coach.addSalary(salary);
                    coaches.add(coach);
                }
            }
        }
        return false;
    }

    @Override
    public Coach getCoach(User user) {
        if(null != user) {
            synchronized (CoachInMemory.class) {
                Optional<Coach> optional = coaches.stream()
                        .filter(coach -> user.equals(coach.getUser()))
                        .findFirst();
                if (optional.isPresent()) {
                    return optional.get();
                }
            }
        }
        return null;
    }

    @Override
    public List<Coach> getCoachList() {
        return new LinkedList<>(coaches);
    }
}
