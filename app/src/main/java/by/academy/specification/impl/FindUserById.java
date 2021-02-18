package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class FindUserById implements ISpecification<User>, IHibernateSpecification {

    private final long userId;

    public FindUserById(long userId) {
        this.userId = userId;
    }

    @Override
    public SimpleExpression getExpression() {
        final String ID = "id";
        return Restrictions.eq(ID, userId);
    }

    @Override
    public boolean isNotCorrect(User user) {
        return this.userId != user.getId();
    }
}
