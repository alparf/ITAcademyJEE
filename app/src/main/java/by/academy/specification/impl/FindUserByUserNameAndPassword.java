package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class FindUserByUserNameAndPassword implements ISpecification<User>, IHibernateSpecification {

    private final String userName;
    private final String password;

    public FindUserByUserNameAndPassword(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public SimpleExpression getExpression() {
        final String USER_NAME = "userName";
        return Restrictions.eq(USER_NAME, this.userName);
    }

    @Override
    public boolean isNotCorrect(User user) {
        if ((null != user) && (null != this.password)) {
            return !((user.getUserName().equals(this.userName)) && (this.password.equals(user.getPassword())));
        }
        return true;
    }
}
