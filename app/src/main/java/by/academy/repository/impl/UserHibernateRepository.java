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
import java.util.Optional;

public class UserHibernateRepository implements IRepository<User> {
    @Override
    public Optional<User> add(User user) {
        Optional<User> optional = Optional.of(user);
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            user.setId((Long) session.save(user));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return optional;
    }

    @Override
    public Optional<User> remove(User user) {
        final String COACH = "coach";
        Optional<User> optional = Optional.of(user);
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
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
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<User> set(User user) {
        Session session = HibernateUtil.getEMFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session){
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.of(user);
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
