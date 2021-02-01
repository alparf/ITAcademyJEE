package by.academy.specification;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.specification.impl.*;

public class UserDBSpecifications {
    public static ISpecification<User> userById(long id) {
        return new UserDBGetById(id);
    }

    public static ISpecification<User>  userByUserName(String userName) {
        return new UserDBGetByUserName(userName);
    }

    public static ISpecification<User>  userByUserNameAndPassword(String userName, String password) {
        return new UserDBGetByUserNameAndPass(userName, password);
    }

    public static ISpecification<User>  allUsers() {
        return new UserDBGetAll();
    }

    public static ISpecification<User>  allUsers(UserType userType) {
        return new UserDBGetAllByType(userType);
    }
}
