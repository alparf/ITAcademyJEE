package by.academy.specification.impl.salary;

import by.academy.model.bean.Salary;
import by.academy.model.bean.User;
import by.academy.specification.IHibernateSpecification;
import by.academy.specification.ISpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CoachIdSpecification implements ISpecification<Salary>, IHibernateSpecification<Salary> {
    private final User coach;

    public CoachIdSpecification(User coach) {
        this.coach = coach;
    }

    @Override
    public CriteriaQuery<Salary> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {
        final String COACH = "coach";
        CriteriaQuery<Salary> criteriaQuery = criteriaBuilder.createQuery(Salary.class);
        Root<Salary> root = criteriaQuery.from(Salary.class);
        return criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(COACH), this.coach));
    }

    @Override
    public boolean isInvalid(Salary salary) {
        return !this.coach.equals(salary.getCoach());
    }
}
