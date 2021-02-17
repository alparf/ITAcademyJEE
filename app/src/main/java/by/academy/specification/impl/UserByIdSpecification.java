package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

public class UserByIdSpecification implements ISpecification<User> {

    private final long userId;

    public UserByIdSpecification(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean isNotCorrect(User user) {
        return false;
    }
}
