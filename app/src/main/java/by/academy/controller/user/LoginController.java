package by.academy.controller.user;

import by.academy.constant.ExceptionConstant;
import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.exception.UserServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class LoginController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter(ServletConstant.USER_NAME);
        String password = req.getParameter(ServletConstant.PASSWORD);
        HttpSession session = req.getSession();
        session.setAttribute(ServletConstant.EXCEPTION_MESSAGE, null);
        Optional<User> user = UserFacade.login(userName, password);
        if(!user.isPresent()) {
            log.info("User = {} not found!", userName);
            session.setAttribute(ServletConstant.EXCEPTION_MESSAGE, ExceptionConstant.INVALID_USER_NAME_OR_PASSWORD);
            session.setAttribute(ServletConstant.USER, null);
        } else {
            log.info("User = {} signed in", userName);
            session.setAttribute(ServletConstant.USER, user.get());
        }
        res.sendRedirect(PageConstant.HOME);
    }

    @Override
    public void init() {
        final String ADMIN_NAME = "adminName";
        final String ADMIN_PASSWORD = "adminPassword";
        String adminName = getServletConfig().getInitParameter(ADMIN_NAME);
        String adminPassword = getServletConfig().getInitParameter(ADMIN_PASSWORD);
        User admin = User.newBuilder()
                .withFio("Jon Snow")
                .withAge(34)
                .withUserName(adminName)
                .withPassword(adminPassword)
                .withUserType(UserType.ADMIN)
                .build();
        try {
            UserFacade.addUser(admin);
        } catch (UserServiceException e) {
            e.printStackTrace();
        }
    }
}
