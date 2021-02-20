package by.academy.service;

import by.academy.model.bean.Salary;

import java.util.List;
import java.util.Optional;

public interface ISalaryService {
    Optional<Salary> newSalary(Salary salary);
    List<Salary> getAll(long coachId);
}
