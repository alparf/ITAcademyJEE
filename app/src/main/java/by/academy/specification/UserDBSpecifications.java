package by.academy.specification;

import by.academy.specification.impl.db.UserDBGetAll;
import by.academy.specification.impl.db.UserDBGetById;
import by.academy.specification.impl.db.UserDBGetByUserName;
import by.academy.specification.impl.db.UserDBLogin;

public class UserDBSpecifications {
    public static IUserSpecification userById(long id) {
        return new UserDBGetById(id);
    }

    public static IUserSpecification userByUserName(String userName) {
        return new UserDBGetByUserName(userName);
    }

    public static IUserSpecification userLogin(String userName, String password) {
        return new UserDBLogin(userName, password);
    }

    public static IUserSpecification allUsers() {
        return new UserDBGetAll();
    }
}
