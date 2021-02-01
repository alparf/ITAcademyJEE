package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class UserDBGetAllByType extends UserDBGetAll {

    private final UserType userType;

    public UserDBGetAllByType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean specificity(User user) {
        boolean isCoach = true;
        if (null != user) {
            if(user.getUserType() == userType) {
                isCoach = false;
            }
        }
        return isCoach;
    }
}
