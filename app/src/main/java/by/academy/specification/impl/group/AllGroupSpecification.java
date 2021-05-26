package by.academy.specification.impl.group;

import by.academy.model.bean.Group;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AllGroupSpecification implements ISpecification<Group>, IHibernateSpecification<Group> {
    @Override
    public CriteriaQuery<Group> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        return criteriaQuery.select(root);
    }

    @Override
    public boolean isInvalid(Group group) {
        return false;
    }
}
