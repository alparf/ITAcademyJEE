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
        ICoachDAO coachDAO = new CoachInMemory();
        return coachDAO.getCoach(user);
    }

    @Override
    public List<Coach> getCoachList() {
        ICoachDAO coachDAO = new CoachInMemory();
        return coachDAO.getCoachList();
    }

    @Override
    public boolean addSalary(Coach coach, Integer salary) {
        ICoachDAO coachDAO = new CoachInMemory();
        return coachDAO.addSalary(coach, salary);
    }
}