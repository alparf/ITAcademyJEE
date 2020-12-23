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
                Optional<Coach> optional = coaches.stream().filter(coach::equals).findFirst();
                if (!optional.isEmpty()) {
                    optional.get().getSalaries().addLast(salary);
                } else {
                    coach.getSalaries().addLast(salary);
                    coaches.add(coach);
                }
            }
        }
        return false;
    }
    @Override
    public Coach getCoach(User user) {
        return null;
    }

    @Override
    public List<Coach> getCoachList() {
        return coaches;
    }
}
