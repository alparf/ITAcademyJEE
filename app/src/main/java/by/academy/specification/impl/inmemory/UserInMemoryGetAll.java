package by.academy.specification.impl.inmemory;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

public class UserInMemoryGetAll implements ISpecification<User> {

    @Override
    public boolean specification(User user) {
        return true;
    }
}
