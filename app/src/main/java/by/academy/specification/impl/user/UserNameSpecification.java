package by.academy.specification.impl.user;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserNameSpecification implements ISpecification<User>, IHibernateSpecification<User> {
    private final String userName;

    public UserNameSpecification(String userName) {
        this.userName = userName;
    }

    @Override
    public CriteriaQuery<User> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        final String USER_NAME = "userName";
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        return criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(USER_NAME), this.userName));
    }



    @Override
    public boolean isInvalid(User user) {
        return !this.userName.equals(user.getUserName());
    }
}
