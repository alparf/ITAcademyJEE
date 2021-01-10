package by.academy.specification.impl.inmemory;

import by.academy.model.bean.User;
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
                    && (this.password.equals(user.getPassword()))) {
                return true;
            }
        }
        return false;
    }
}
