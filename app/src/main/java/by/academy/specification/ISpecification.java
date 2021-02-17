package by.academy.specification;

public interface ISpecification<T> {
    boolean isNotCorrect(T t);
}
