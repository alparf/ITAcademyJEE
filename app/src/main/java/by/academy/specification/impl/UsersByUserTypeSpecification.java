package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class UsersByUserTypeSpecification extends UsersSpecification {

    private final UserType userType;

    public UsersByUserTypeSpecification(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean isSpecific(User user) {
        boolean isCoach = true;
        if (null != user) {
            if(user.getUserType() == userType) {
                isCoach = false;
            }
        }
        return isCoach;
    }
}
