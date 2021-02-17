package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;
import org.hibernate.Session;

import java.util.List;

public class SalaryRepositoryHibernate implements IRepository<Salary> {

    @Override
    public boolean add(Salary salary) {
        return false;
    }

    @Override
    public boolean remove(Salary salary) {
        return false;
    }

    @Override
    public boolean set(Salary salary) {
        return false;
    }

    @Override
    public List<Salary> query(ISpecification<Salary> specification) {
        Session session = HibernateUtil.getEMFactory().openSession();
        List<Salary> userList = session.createCriteria(Salary.class).list();
        userList.removeIf(specification::isNotCorrect);
        return userList;
    }
}
