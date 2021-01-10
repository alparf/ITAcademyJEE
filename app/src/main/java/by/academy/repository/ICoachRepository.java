package by.academy.repository;

import by.academy.model.bean.Coach;
import by.academy.specification.ICoachSpecification;

import java.util.List;

public interface ICoachRepository {
    void addCoach(Coach coach);
    void removeCoach(Coach coach);
    void setCoach(Coach coach);
    void addSalary(Coach coach, int salary);
    List<Coach> query(ICoachSpecification specification);
}
