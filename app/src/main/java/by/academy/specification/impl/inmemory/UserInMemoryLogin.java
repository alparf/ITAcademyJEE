package by.academy.specification.impl.inmemory;

import by.academy.model.bean.User;
import by.academy.model.factory.UserFactory;
import by.academy.specification.IUserSpecification;

public class UserInMemoryLogin implements IUserSpecification {

    private String userName;
    private String password;

    public UserInMemoryLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean specification(User user) {
        if ((null != user) && (null != this.password)) {
            if ((user.getUserName().equals(this.userName))
                    && (UserFactory.PASS_AUTH.authenticate(password.toCharArray(), user.getPassword()))) {
                return true;
            }
        }
        return false;
    }
}
