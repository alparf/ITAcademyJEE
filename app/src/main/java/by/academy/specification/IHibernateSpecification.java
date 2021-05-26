package by.academy.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface IHibernateSpecification<T> {
    CriteriaQuery<T> getCriteriaQuery(CriteriaBuilder criteriaBuilder);
}
