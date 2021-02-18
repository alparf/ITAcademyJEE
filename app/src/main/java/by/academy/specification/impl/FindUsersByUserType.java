package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class FindUsersByUserType extends FindAllUsers {

    private final UserType userType;

    public FindUsersByUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean isNotCorrect(User user) {
        boolean isCoach = true;
        if (null != user) {
            if(user.getUserType() == userType) {
                isCoach = false;
            }
        }
        return isCoach;
    }
}
