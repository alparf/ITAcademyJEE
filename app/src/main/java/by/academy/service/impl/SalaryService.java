package by.academy.service.impl;

import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.repository.impl.SalaryHibernateRepository;
import by.academy.service.ISalaryService;
import by.academy.specification.impl.salary.CoachIdSpecification;

import java.util.List;
import java.util.Optional;

public class SalaryService implements ISalaryService {
    private static volatile SalaryService service;
    private final IRepository<Salary> repository = new SalaryHibernateRepository();

    public static SalaryService getService() {
        if (null == service) {
            synchronized (SalaryService.class) {
                if (null == service) {
                    service = new SalaryService();
                }
            }
        }
        return service;
    }
    @Override
    public Optional<Salary> addSalary(Salary salary) {
        return this.repository.add(salary);
    }

    @Override
    public List<Salary> findAllSalaries(long coachId) {
        User coach = UserService.getService().findUser(coachId).get();
        return this.repository.query(new CoachIdSpecification(coach));
    }

    private SalaryService() {

    }
}
