package by.academy.specification.impl.user;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class UserNameSpecification implements ISpecification<User>, IHibernateSpecification {
    private final String userName;

    public UserNameSpecification(String userName) {
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
