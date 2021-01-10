package by.academy.specification.impl.jdbc;

import by.academy.constant.SqlConstant;
import by.academy.model.bean.User;
import by.academy.specification.ISqlSpecification;
import by.academy.specification.IUserSpecification;

public class UserDBGetAll implements IUserSpecification, ISqlSpecification {

    @Override
    public String toSqlCauses() {
        return SqlConstant.SELECT_USERS;
    }

    @Override
    public boolean specification(User user) {
        return true;
    }
}
