package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Group;
import by.academy.repository.AbstractHibernateRepository;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GroupHibernateRepository extends AbstractHibernateRepository implements IRepository<Group> {
    @Override
    public Optional<Group> add(Group group) {
        Optional<Group> optional = Optional.of(group);
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            group.setId((Long) session.save(group));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return optional;
    }

    @Override
    public Optional<Group> remove(Group group) {
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.delete(group);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.of(group);
    }

    @Override
    public Optional<Group> set(Group group) {
        Session session = HibernateUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.update(group);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return Optional.of(group);
    }

    @Override
    public List<Group> query(ISpecification<Group> specification) {
        Session session = HibernateUtil.getFactory().openSession();
        List<Group> userList = new LinkedList<>();
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
