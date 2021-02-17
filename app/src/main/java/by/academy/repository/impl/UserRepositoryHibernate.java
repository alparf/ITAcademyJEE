package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.User;
import by.academy.repository.IRepository;
import by.academy.specification.ISpecification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepositoryHibernate implements IRepository<User> {

    @Override
    public boolean add(User user) {
        boolean added = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
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
    public boolean remove(User user) {
        boolean deleted = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            deleted = false;
        } finally {
            session.close();
        }
        return deleted;
    }

    @Override
    public boolean set(User user) {
        boolean updated = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            updated = false;
        } finally {
            session.close();
        }
        return updated;
    }

    @Override
    public List<User> query(ISpecification<User> specification) {
        Session session = HibernateUtil.getEMFactory().openSession();
        List<User> userList = session.createCriteria(User.class).list();
        userList.removeIf(specification::isNotCorrect);
        return userList;
    }
}
