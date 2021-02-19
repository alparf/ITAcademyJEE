package by.academy.repository.impl;

import by.academy.connection.HibernateUtil;
import by.academy.model.bean.Group;
import by.academy.repository.AbstractHibernateRepository;
import by.academy.repository.IRepository;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class GroupHibernateRepository extends AbstractHibernateRepository implements IRepository<Group> {
    @Override
    public Optional<Group> add(Group group) {
        return Optional.empty();
    }

    @Override
    public Optional<Group> remove(Group group) {
        return Optional.empty();
    }

    @Override
    public Optional<Group> set(Group group) {
        return Optional.empty();
    }

    @Override
    public List<Group> query(ISpecification<Group> specification) {
        Session session = HibernateUtil.getEMFactory().openSession();
        Criteria criteria = session.createCriteria(Group.class);
        List<Group> groupList = criteria.list();
        if (specification instanceof IHibernateSpecification) {
            IHibernateSpecification hibernateSpecification = (IHibernateSpecification) specification;
            criteria.add(hibernateSpecification.getExpression());
        }
        groupList.removeIf(specification::isNotCorrect);
        closeSession(session);
        return groupList;
    }
}
