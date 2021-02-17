package by.academy.service.impl;

import by.academy.model.bean.Salary;
import by.academy.service.ISalaryService;

import java.util.List;

public class SalaryService implements ISalaryService {

    @Override
    public boolean addSalary(Salary salary) {
        return false;
    }

    @Override
    public List<Salary> getAllByCoachId(long coachId) {
        return null;
    }
}
