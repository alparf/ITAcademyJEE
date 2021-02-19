package by.academy.specification.impl.salary;

import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class CoachIdSpecification implements ISpecification<Salary>, IHibernateSpecification {
    private final User coach;

    public CoachIdSpecification(User coach) {
        this.coach = coach;
    }

    @Override
    public SimpleExpression getExpression() {
        final String COACH = "coach";
        return Restrictions.eq(COACH, coach);
    }

    @Override
    public boolean isNotCorrect(Salary salary) {
        return this.coach.getId() != salary.getCoach().getId();
    }
}
