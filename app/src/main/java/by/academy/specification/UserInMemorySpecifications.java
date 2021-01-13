package by.academy.specification;

import by.academy.specification.impl.inmemory.UserInMemoryGetAll;
import by.academy.specification.impl.inmemory.UserInMemoryGetById;
import by.academy.specification.impl.inmemory.UserInMemoryGetByUserName;
import by.academy.specification.impl.inmemory.UserInMemoryLogin;

public class UserInMemorySpecifications {
    public static IUserSpecification userByUserName(String userName) {
        return new UserInMemoryGetByUserName(userName);
    }

    public static IUserSpecification userById(long id) {
        return new UserInMemoryGetById(id);
    }

    public static IUserSpecification userLogin(String userName, String password) {
        return new UserInMemoryLogin(userName, password);
    }

    public static IUserSpecification allUsers() {
        return new UserInMemoryGetAll();
    }
}
