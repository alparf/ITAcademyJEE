package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class FindUserByUserName implements ISpecification<User>, IHibernateSpecification {

    private final String userName;

    public FindUserByUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public SimpleExpression getExpression() {
        final String USER_NAME = "userName";
        return Restrictions.eq(USER_NAME, this.userName);
    }

    @Override
    public boolean isNotCorrect(User user) {
        return !this.userName.equals(user.getUserName());
    }
}
