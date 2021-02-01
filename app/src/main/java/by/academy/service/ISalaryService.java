package by.academy.service;

import by.academy.model.bean.Salary;

import java.util.List;

public interface ISalaryService {
    boolean addSalary(Salary salary);
    List<Salary> getAllByCoachId(long coachId);
}
