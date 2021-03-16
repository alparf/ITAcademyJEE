package by.academy.specification.impl.user;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserIdSpecification implements ISpecification<User>, IHibernateSpecification<User> {
    private final long userId;

    public UserIdSpecification(long userId) {
        this.userId = userId;
    }

    @Override
    public CriteriaQuery<User> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        final String ID = "id";
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        return criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(ID), this.userId));
    }

    @Override
    public boolean isInvalid(User user) {
        return this.userId != user.getId();
    }
}
