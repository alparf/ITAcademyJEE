package by.academy.strategy.impl;

import by.academy.constant.SessionConstant;
import by.academy.facade.UserFacade;
import by.academy.strategy.IUserStrategy;

import javax.servlet.http.HttpSession;

public class AdminStrategy implements IUserStrategy {

    HttpSession session;

    public static IUserStrategy create(HttpSession session) {
        return new AdminStrategy(session);
    }

    @Override
    public void sessionInit() {
        session.setAttribute(SessionConstant.USER_LIST, UserFacade.getAllUsers());
        session.setAttribute(SessionConstant.COACH_LIST, UserFacade.getAllCoaches());
    }

    private AdminStrategy(HttpSession session) {
        this.session = session;
    }
}