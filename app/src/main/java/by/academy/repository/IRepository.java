package by.academy.repository;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    Optional<T> add(T t);
    Optional<T> remove(T t);
    Optional<T> set(T t);
    List<T> query(ISpecification<T> specification);
}
