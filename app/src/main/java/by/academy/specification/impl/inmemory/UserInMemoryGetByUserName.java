package by.academy.specification.impl.inmemory;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

public class UserInMemoryGetByUserName implements IUserSpecification {

    private final String userName;

    public UserInMemoryGetByUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean specification(User user) {
        if ((null != user) && (null != this.userName)) {
            return this.userName.equals(user.getUserName());
        }
        return false;
    }
}
