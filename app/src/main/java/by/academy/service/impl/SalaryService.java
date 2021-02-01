package by.academy.service.impl;

import by.academy.model.bean.Salary;
import by.academy.repository.IRepository;
import by.academy.repository.impl.SalaryRepositoryDB;
import by.academy.service.ISalaryService;
import by.academy.specification.impl.SalaryDBGetByCoachId;

import java.util.List;

public class SalaryService implements ISalaryService {

    @Override
    public boolean addSalary(Salary salary) {
        return new SalaryRepositoryDB().add(salary);
    }

    @Override
    public List<Salary> getAllByCoachId(long coachId) {
        IRepository<Salary> repository = new SalaryRepositoryDB();
        List<Salary> salaries = repository.query(new SalaryDBGetByCoachId(coachId));
        return salaries;
    }
}
