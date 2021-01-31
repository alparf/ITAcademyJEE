package by.academy.repository;

import by.academy.specification.ISpecification;

import java.util.List;

public interface IRepository<T> {
    boolean addUser(T t);
    boolean removeUser(T t);
    boolean setUser(T t);
    List<T> query(ISpecification<T> specification);
}
