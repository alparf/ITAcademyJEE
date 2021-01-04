package by.academy.service.impl;

import by.academy.dao.ICoachDAO;
import by.academy.dao.impl.CoachInMemory;
import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.service.ICoachService;

import java.util.List;

public class CoachService implements ICoachService {
    @Override
    public Coach getCoach(User user) {
        return null;
    }

    @Override
    public List<Coach> getCoachList() {
        return null;
    }

    @Override
    public boolean addSalary(Coach coach, Integer salary) {
        ICoachDAO coachDAO = new CoachInMemory();
        return coachDAO.addSalary(coach, salary);
    }
}
