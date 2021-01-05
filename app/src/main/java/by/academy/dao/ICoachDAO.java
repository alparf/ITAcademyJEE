package by.academy.dao;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.Map;

public interface ICoachDAO {
    boolean addSalary(Coach coach, Integer salary);
    Coach getCoach(User user);
    Map<String, Coach> getCoachList();
}
