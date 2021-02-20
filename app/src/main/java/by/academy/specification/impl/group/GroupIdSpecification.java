package by.academy.specification.impl.group;

import by.academy.model.bean.Group;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class GroupIdSpecification implements ISpecification<Group>, IHibernateSpecification {
    private final Long groupId;

    public GroupIdSpecification(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public SimpleExpression getExpression() {
        final String ID = "id";
        return Restrictions.eq(ID, this.groupId);
    }

    @Override
    public boolean isNotCorrect(Group group) {
        return false;
    }
}
