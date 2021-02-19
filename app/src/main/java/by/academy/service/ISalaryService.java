package by.academy.service;

import by.academy.model.bean.Salary;

import java.util.List;
import java.util.Optional;

public interface ISalaryService {
    Optional<Salary> addSalary(Salary salary);
    List<Salary> getAllByCoachId(long coachId);
}
