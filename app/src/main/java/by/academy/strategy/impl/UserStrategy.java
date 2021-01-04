package by.academy.strategy.impl;

import by.academy.strategy.IUserStrategy;

import javax.servlet.http.HttpSession;

public class UserStrategy implements IUserStrategy {

    HttpSession session;

    public static IUserStrategy create(HttpSession session) {
        return new UserStrategy(session);
    }

    @Override
    public void sessionInit() {

    }

    private UserStrategy(HttpSession session) {
        this.session = session;
    }
}
