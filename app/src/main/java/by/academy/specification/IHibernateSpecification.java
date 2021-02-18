package by.academy.specification;

import org.hibernate.criterion.SimpleExpression;

public interface IHibernateSpecification {
    SimpleExpression getExpression();
}
