package by.academy.service;

import by.academy.model.bean.Coach;

import java.util.List;

public interface ICoachService {
    List<Coach> getAll();
    void addSalary(long coachId, int salary);
}
