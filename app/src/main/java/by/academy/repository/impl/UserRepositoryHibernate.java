package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserRepositoryHibernate implements IRepository<User> {

    @Override
    public boolean add(User user) {
        boolean isSaved = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
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
    public boolean remove(User user) {
        boolean isDeleted = true;
        final String COACH = "coach";
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            user = session.load(User.class, user.getId());
            if (UserType.COACH == user.getUserType()) {
                Criteria criteria = session.createCriteria(Salary.class);
                criteria.add(Restrictions.eq(COACH, user));
                criteria.list().forEach(session::delete);
            }
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            isDeleted = false;
        } finally {
            session.close();
        }
        return isDeleted;
    }

    @Override
    public boolean set(User user) {
        boolean isUpdated = true;
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
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
    public List<User> query(ISpecification<User> specification) {
        Session session = HibernateUtil.getEMFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        if (specification instanceof IHibernateSpecification) {
            IHibernateSpecification hibernateSpecification = (IHibernateSpecification) specification;
            criteria.add(hibernateSpecification.getExpression());
        }
        List<User> userList = criteria.list();
        userList.removeIf(specification::isNotCorrect);
        return userList;
    }
}
