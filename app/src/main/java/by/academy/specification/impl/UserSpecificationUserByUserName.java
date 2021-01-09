package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.IUserSpecification;

public class UserSpecificationUserByUserName implements IUserSpecification {

    private String userName;

    public UserSpecificationUserByUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean specification(User user) {
        if ((null != user) && (null != this.userName)) {
            if (this.userName.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }
}
