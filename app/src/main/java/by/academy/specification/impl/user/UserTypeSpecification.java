package by.academy.specification.impl.user;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public class UserTypeSpecification extends AllUsersSpecification {

    private final UserType userType;

    public UserTypeSpecification(UserType userType) {
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
