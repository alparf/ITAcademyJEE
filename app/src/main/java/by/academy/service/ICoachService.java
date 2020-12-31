package by.academy.service;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.List;

public interface ICoachService {
    Coach getCoach(User user);
    List<Coach> getAll();
    boolean addSalary(Coach coach, Integer salary);
}
