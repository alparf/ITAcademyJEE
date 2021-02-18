package by.academy.service.impl;

import by.academy.model.bean.Salary;
import by.academy.repository.IRepository;
import by.academy.repository.impl.SalaryRepositoryHibernate;
import by.academy.service.ISalaryService;
import by.academy.specification.impl.SalaryByCoachIdSpecification;

import java.util.List;

public class SalaryService implements ISalaryService {

    @Override
    public boolean addSalary(Salary salary) {
        IRepository<Salary> salaryIRepository = new SalaryRepositoryHibernate();
        return salaryIRepository.add(salary);
    }

    @Override
    public List<Salary> getAllByCoachId(long coachId) {
        IRepository<Salary> salaryIRepository = new SalaryRepositoryHibernate();
        return salaryIRepository.query(new SalaryByCoachIdSpecification(coachId));
    }
}
