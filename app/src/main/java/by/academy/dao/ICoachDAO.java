package by.academy.dao;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.List;

public interface ICoachDAO {
    boolean addSalary(Coach coach, Integer salary);
    Coach getCoach(User user);
    List<Coach> getCoachList();
}
