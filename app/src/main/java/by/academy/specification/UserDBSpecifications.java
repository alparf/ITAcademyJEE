package by.academy.specification;

import by.academy.specification.impl.db.UserDBGetAll;
import by.academy.specification.impl.db.UserDBGetById;
import by.academy.specification.impl.db.UserDBGetByUserName;
import by.academy.specification.impl.db.UserDBGetByUserNameAndPass;

public class UserDBSpecifications {
    public static IUserSpecification userById(long id) {
        return new UserDBGetById(id);
    }

    public static IUserSpecification userByUserName(String userName) {
        return new UserDBGetByUserName(userName);
    }

    public static IUserSpecification userByUserNameAndPassword(String userName, String password) {
        return new UserDBGetByUserNameAndPass(userName, password);
    }

    public static IUserSpecification allUsers() {
        return new UserDBGetAll();
    }
}
