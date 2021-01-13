package by.academy.strategy.impl;

import by.academy.constant.ServletConstant;
import by.academy.facade.UserFacade;
import by.academy.strategy.IUserStrategy;

import javax.servlet.http.HttpServletRequest;

public class AdminStrategy implements IUserStrategy {

    private final HttpServletRequest request;

    public static IUserStrategy create(HttpServletRequest request) {
        return new AdminStrategy(request);
    }

    @Override
    public void sessionInit() {
        request.setAttribute(ServletConstant.USER_LIST, UserFacade.getAllUsers());
        request.setAttribute(ServletConstant.COACH_LIST, UserFacade.getAllCoaches());
    }

    private AdminStrategy(HttpServletRequest request) {
        this.request = request;
    }
}