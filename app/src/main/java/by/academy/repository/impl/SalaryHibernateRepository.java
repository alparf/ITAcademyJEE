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
import java.util.Optional;

public class SalaryHibernateRepository implements IRepository<Salary> {

    @Override
    public Optional<Salary> add(Salary salary) {
        Optional<Salary> optional = Optional.of(salary);
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            salary.setId((Long) session.save(salary));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return optional;
    }

    @Override
    public Optional<Salary> remove(Salary salary) {
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.of(salary);
    }

    @Override
    public Optional<Salary> set(Salary salary) {
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.of(salary);
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
