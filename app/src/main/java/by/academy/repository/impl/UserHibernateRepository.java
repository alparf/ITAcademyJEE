package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserHibernateRepository implements IRepository<User> {
    @Override
    public Optional<User> add(User user) {
        Optional<User> optional = Optional.of(user);
        Session session = HibernateUtil.getFactory().openSession();
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
        final String COACH_ID = "coach_id";
        Optional<User> optional = Optional.of(user);
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            user = session.load(User.class, user.getId());
            if (UserType.COACH == user.getUserType()) {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<Salary> criteriaQuery = criteriaBuilder.createQuery(Salary.class);
                Root<Salary> root = criteriaQuery.from(Salary.class);
                session.createQuery(criteriaQuery.select(root)
                        .where(criteriaBuilder.equal(root.get(COACH_ID), user.getId())))
                        .getResultList()
                        .forEach(session::delete);
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
        Session session = HibernateUtil.getFactory().openSession();
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
        Session session = HibernateUtil.getFactory().openSession();
        List<User> userList = new LinkedList<>();
        try (session) {
            if (specification instanceof IHibernateSpecification) {
                IHibernateSpecification hibernateSpecification = (IHibernateSpecification) specification;
                userList = session.createQuery(hibernateSpecification.getCriteriaQuery(session.getCriteriaBuilder()))
                        .getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userList.removeIf(specification::isInvalid);
        return userList;
    }
}
