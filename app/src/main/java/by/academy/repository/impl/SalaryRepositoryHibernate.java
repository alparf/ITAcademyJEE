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
        boolean isSaved = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            isSaved = false;
        } finally {
            session.close();
        }
        return isSaved;
    }

    @Override
    public boolean remove(Salary salary) {
        boolean isDeleted = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            isDeleted = false;
        }
        return isDeleted;
    }

    @Override
    public boolean set(Salary salary) {
        boolean isUpdated = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            isUpdated = false;
        } finally {
            session.close();
        }
        return isUpdated;
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
