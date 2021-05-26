package by.academy.specification.impl.group;

import by.academy.model.bean.Group;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GroupIdSpecification implements ISpecification<Group>, IHibernateSpecification<Group> {
    private final long groupId;

    public GroupIdSpecification(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public CriteriaQuery<Group> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        final String ID = "id";
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        return criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(ID), this.groupId));
    }

    @Override
    public boolean isInvalid(Group group) {
        return false;
    }
}
