package by.academy.controller.user;

import by.academy.constant.ExceptionConstant;
import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.controller.AbstractController;
import by.academy.dao.impl.UserInMemory;
import by.academy.exception.UserServiceException;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class LoginController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(UserInMemory.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter(SessionConstant.USER_NAME);
        String password = req.getParameter(SessionConstant.PASSWORD);
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, null);
        User user = null;
        try {
            user = UserFacade.userLogin(userName, password);
        } catch (UserServiceException e) {
            log.error(e.getMessage(), e);
            session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, ExceptionConstant.USER_NOT_FOUND);
        }
        session.setAttribute(SessionConstant.USER, user);
        RequestDispatcher dispatcher = req.getRequestDispatcher(File.separator + ServletConstant.HOME_CONTROLLER);
        dispatcher.forward(req, res);
    }

    @Override
    public void init() {
        final String ADMIN_NAME = "adminName";
        final String ADMIN_PASSWORD = "adminPassword";
        String adminName = getServletConfig().getInitParameter(ADMIN_NAME);
        String adminPassword = getServletConfig().getInitParameter(ADMIN_PASSWORD);
        User admin = UserFactory.createUser(
                "FirstName LastName", 34, adminName, adminPassword, UserType.ADMIN);
        UserFacade.addUser(admin);
    }
}
