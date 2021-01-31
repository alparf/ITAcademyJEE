package by.academy.specification.impl.db;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class UserDBGetAllByType extends UserDBGetAll {

    private final UserType userType;

    public UserDBGetAllByType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean specification(User user) {
        boolean isCoach = false;
        if (null != user) {
            if(user.getUserType() == userType) {
                isCoach = true;
            }
        }
        return isCoach;
    }
}
