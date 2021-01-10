package by.academy.specification.impl.jdbc;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.User;
import by.academy.model.factory.UserFactory;
import by.academy.specification.ISqlSpecification;
import by.academy.specification.IUserSpecification;

public class UserDBLogin implements IUserSpecification, ISqlSpecification {

    private final String userName;
    private final String password;

    public UserDBLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toSqlCauses() {
        return SqlConstant.SELECT_USER;
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
