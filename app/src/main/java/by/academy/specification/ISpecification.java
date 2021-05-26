package by.academy.specification;

public interface ISpecification<T> {
    boolean isInvalid(T t);
}
