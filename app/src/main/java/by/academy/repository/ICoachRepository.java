package by.academy.repository;

import by.academy.model.bean.Coach;
import by.academy.specification.ICoachSpecification;

import java.util.List;

public interface ICoachRepository {
    boolean addSalary(Coach coach, int salary);
    List<Coach> query(ICoachSpecification specification);
}
