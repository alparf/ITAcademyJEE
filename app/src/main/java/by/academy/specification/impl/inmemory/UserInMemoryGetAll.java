package by.academy.specification.impl.inmemory;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

public class UserInMemoryGetAll implements IUserSpecification {

    @Override
    public boolean specification(User user) {
        return true;
    }
}
