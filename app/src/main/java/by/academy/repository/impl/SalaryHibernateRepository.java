package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Salary;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SalaryHibernateRepository implements IRepository<Salary> {
    @Override
    public Optional<Salary> add(Salary salary) {
        Optional<Salary> optional = Optional.of(salary);
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            salary.setId((Long) session.save(salary));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return optional;
    }

    @Override
    public Optional<Salary> remove(Salary salary) {
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.delete(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.of(salary);
    }

    @Override
    public Optional<Salary> set(Salary salary) {
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.update(salary);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.of(salary);
    }

    @Override
    public List<Salary> query(ISpecification<Salary> specification) {
        Session session = HibernateUtil.getFactory().openSession();
        List<Salary> userList = new LinkedList<>();
        try (session) {
            if (specification instanceof IHibernateSpecification) {
                IHibernateSpecification hibernateSpecification = (IHibernateSpecification) specification;
                userList = session.createQuery(hibernateSpecification.getCriteriaQuery(session.getCriteriaBuilder()))
                        .getResultList();
            }
        }
        userList.removeIf(specification::isInvalid);
        return userList;
    }
}
