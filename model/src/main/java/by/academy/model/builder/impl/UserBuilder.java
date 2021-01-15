package by.academy.model.builder.impl;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.builder.IUserBuilder;

public class UserBuilder implements IUserBuilder {

    private User user;

    public UserBuilder() {
        this.user = new User();
    }

    public User build() {
        User builtUser = this.user;
        this.user = new User();
        return builtUser;
    }

    @Override
    public IUserBuilder withId(long id) {
        this.user.setId(id);
        return this;
    }

    @Override
    public IUserBuilder withFio(String fio) {
        this.user.setFio(fio);
        return this;
    }

    @Override
    public IUserBuilder withAge(int age) {
        this.user.setAge(age);
        return this;
    }

    @Override
    public IUserBuilder withUserName(String userName) {
        this.user.setUserName(userName);
        return this;
    }

    @Override
    public IUserBuilder withPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    @Override
    public IUserBuilder withUserType(UserType userType) {
        this.user.setUserType(userType);
        return this;
    }
}
