package by.academy.specification.impl.user;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

public class AllUsersSpecification implements ISpecification<User> {

    @Override
    public boolean isNotCorrect(User user) {
        return false;
    }
}
