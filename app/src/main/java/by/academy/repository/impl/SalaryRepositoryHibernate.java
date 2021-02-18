package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Salary;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SalaryRepositoryHibernate implements IRepository<Salary> {

    @Override
    public boolean add(Salary salary) {
        boolean added = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            added = false;
        } finally {
            session.close();
        }
        return added;
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
        Criteria criteria = session.createCriteria(Salary.class);
        if (specification instanceof IHibernateSpecification) {
            IHibernateSpecification hibernateSpecification = (IHibernateSpecification) specification;
            criteria.add(hibernateSpecification.getExpression());
        }
        List<Salary> salaryList = criteria.list();
        salaryList.removeIf(specification::isNotCorrect);
        return salaryList;
    }
}
