package by.academy.service;

import by.academy.model.bean.Coach;

import java.util.List;

public interface ICoachService {
    List<Coach> getAll();
    boolean addSalary(long coachId, int salary);
}
