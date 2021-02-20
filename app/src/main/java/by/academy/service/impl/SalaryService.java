package by.academy.service.impl;

import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.repository.impl.SalaryHibernateRepository;
import by.academy.repository.impl.UserHibernateRepository;
import by.academy.service.ISalaryService;
import by.academy.specification.impl.salary.CoachIdSpecification;
import by.academy.specification.impl.user.UserIdSpecification;

import java.util.List;
import java.util.Optional;

public class SalaryService implements ISalaryService {
    @Override
    public Optional<Salary> newSalary(Salary salary) {
        IRepository<Salary> salaryIRepository = new SalaryHibernateRepository();
        return salaryIRepository.add(salary);
    }

    @Override
    public List<Salary> getAll(long coachId) {
        IRepository<Salary> salaryIRepository = new SalaryHibernateRepository();
        IRepository<User> userIRepository = new UserHibernateRepository();
        User coach = userIRepository.query(new UserIdSpecification(coachId)).stream()
                .findFirst()
                .get();
        return salaryIRepository.query(new CoachIdSpecification(coach));
    }
}
