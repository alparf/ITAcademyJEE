package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

public class UserSpecificationGetAll implements IUserSpecification {

    @Override
    public boolean specification(User user) {
        return true;
    }
}
