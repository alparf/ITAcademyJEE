package by.academy.specification.impl.inmemory;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

public class UserInMemoryGetById implements IUserSpecification {

    private long id;

    public UserInMemoryGetById(long id) {
        this.id = id;
    }

    @Override
    public boolean specification(User user) {
        if (null != user) {
            return user.getId() == id;
        }
        return false;
    }
}
