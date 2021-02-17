package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

public class UserByUserNameSpecification implements ISpecification<User> {

    private final String userName;

    public UserByUserNameSpecification(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isNotCorrect(User user) {
        return false;
    }
}
