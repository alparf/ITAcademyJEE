package by.academy.controller;

import by.academy.constant.JSPConstant;
import by.academy.constant.SessionConstant;
import by.academy.exception.UserNotFoundException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter(SessionConstant.USER_NAME);
        String password = req.getParameter(SessionConstant.PASSWORD);
        IUserService service = new UserService();
        User user;
        try {
            user = service.userLogin(userName, password);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            user = null;
        }
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.USER, user);
        res.sendRedirect(JSPConstant.HOME);
    }

    @Override
    public void init() throws ServletException {
        final String ADMIN_NAME = "adminName";
        final String ADMIN_PASSWORD = "adminPassword";
        String adminName = getServletConfig().getInitParameter(ADMIN_NAME);
        String adminPassword = getServletConfig().getInitParameter(ADMIN_PASSWORD);
        User admin = UserFactory.createUser(
                "FirstName LastName", 34, adminName, adminPassword, UserType.ADMIN);
        UserService service = new UserService();
        service.addUser(admin);
    }
}
