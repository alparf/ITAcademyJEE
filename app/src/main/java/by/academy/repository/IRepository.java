package by.academy.repository;

import by.academy.specification.ISpecification;

import java.util.List;

public interface IRepository<T> {
    boolean add(T t);
    boolean remove(T t);
    boolean set(T t);
    List<T> query(ISpecification<T> specification);
}
