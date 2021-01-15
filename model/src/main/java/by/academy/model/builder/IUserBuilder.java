package by.academy.model.builder;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

public interface IUserBuilder {
    IUserBuilder withId(long id);
    IUserBuilder withFio(String fio);
    IUserBuilder withAge(int age);
    IUserBuilder withUserName(String userName);
    IUserBuilder withPassword(String password);
    IUserBuilder withUserType(UserType userType);
    User build();
}
