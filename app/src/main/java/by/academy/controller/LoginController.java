package by.academy.controller;

import by.academy.exception.UserNotFoundException;
import by.academy.model.bean.User;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import static by.academy.constant.FileConstant.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.constant.SessionConstant.*;

public class LoginController extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter(USER_NAME);
        String password = req.getParameter(PASSWORD);
        IUserService service = new UserService();
        User user;
        try {
            user = service.userLogin(userName, password);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            user = null;
        }
        HttpSession session = req.getSession();
        session.setAttribute(USER, user);
        res.sendRedirect(HOME);
    }

    @Override
    public void init() throws ServletException {
        User admin = new User();
        String adminName = getServletConfig().getInitParameter(ADMIN_NAME);
        String adminPassword = getServletConfig().getInitParameter(ADMIN_PASSWORD);
        admin.setUserName(adminName);
        admin.setPassword(adminPassword);
        UserService service = new UserService();
        service.addUser(admin);
    }
}
