package by.academy.specification.impl.group;

import by.academy.model.bean.Group;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.SimpleExpression;

public class AllGroupSpecification implements ISpecification<Group>, IHibernateSpecification {
    @Override
    public SimpleExpression getExpression() {
        return null;
    }

    @Override
    public boolean isNotCorrect(Group group) {
        return false;
    }
}
