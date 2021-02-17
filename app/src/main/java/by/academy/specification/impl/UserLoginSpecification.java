package by.academy.specification.impl;

import by.academy.model.bean.User;
import by.academy.specification.ISpecification;

public class UserLoginSpecification implements ISpecification<User> {

    private final String userName;
    private final String password;

    public UserLoginSpecification(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean isNotCorrect(User user) {
        if ((null != user) && (null != this.password)) {
            return !((user.getUserName().equals(this.userName)) && (this.password.equals(user.getPassword())));
        }
        return true;
    }
}
