package by.academy.service;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.List;

public interface ICoachService {
    List<Coach> getAll();
    void addSalary(Coach coach, Integer salary);
}
