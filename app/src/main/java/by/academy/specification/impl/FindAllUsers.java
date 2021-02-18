package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

public class FindAllUsers implements ISpecification<User> {

    @Override
    public boolean isNotCorrect(User user) {
        return false;
    }
}
