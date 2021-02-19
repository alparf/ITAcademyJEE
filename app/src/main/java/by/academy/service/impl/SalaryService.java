package by.academy.service.impl;

import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.repository.impl.SalaryHibernateRepository;
import by.academy.repository.impl.UserHibernateRepository;
import by.academy.service.ISalaryService;
import by.academy.specification.impl.salary.CoachIdSpecification;
import by.academy.specification.impl.user.IdSpecification;

import java.util.List;
import java.util.Optional;

public class SalaryService implements ISalaryService {

    @Override
    public Optional<Salary> addSalary(Salary salary) {
        IRepository<Salary> salaryIRepository = new SalaryHibernateRepository();
        return salaryIRepository.add(salary);
    }

    @Override
    public List<Salary> getAllByCoachId(long coachId) {
        IRepository<Salary> salaryIRepository = new SalaryHibernateRepository();
        IRepository<User> userIRepository = new UserHibernateRepository();
        User coach = userIRepository.query(new IdSpecification(coachId)).stream()
                .findFirst()
                .get();
        return salaryIRepository.query(new CoachIdSpecification(coach));
    }
}
