package by.academy.specification.impl.user;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserNameAndPasswordSpecification implements ISpecification<User>, IHibernateSpecification<User> {
    private final String userName;
    private final String password;

    public UserNameAndPasswordSpecification(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
        if ((null != user) && (null != this.password)) {
            return !((user.getUserName().equals(this.userName)) && (this.password.equals(user.getPassword())));
        }
        return true;
    }
}
