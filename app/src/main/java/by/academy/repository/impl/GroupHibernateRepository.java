package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Group;
import by.academy.repository.AbstractHibernateRepository;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class GroupHibernateRepository extends AbstractHibernateRepository implements IRepository<Group> {
    @Override
    public Optional<Group> add(Group group) {
        Optional<Group> optional = Optional.of(group);
        Session session = HibernateUtil.getEMFactory().openSession();
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
        Session session = HibernateUtil.getEMFactory().openSession();
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
        Session session = HibernateUtil.getEMFactory().openSession();
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
        Session session = HibernateUtil.getEMFactory().openSession();
        Criteria criteria = session.createCriteria(Group.class);
        List<Group> groupList = criteria.list();
        if (specification instanceof IHibernateSpecification) {
            criteria.add(((IHibernateSpecification) specification).getExpression());
        }
        groupList.removeIf(specification::isNotCorrect);
        closeSession(session);
        return groupList;
    }
}
