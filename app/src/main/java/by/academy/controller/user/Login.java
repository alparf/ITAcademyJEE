package by.academy.controller.user;

import by.academy.constant.ExceptionMessage;
import by.academy.constant.PageName;
import by.academy.constant.ServletProperties;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.exception.UserServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class Login extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userName = req.getParameter(ServletProperties.USER_NAME);
        String password = req.getParameter(ServletProperties.PASSWORD);
        HttpSession session = req.getSession();
        session.setAttribute(ServletProperties.EXCEPTION_MESSAGE, null);
        Optional<User> user = UserFacade.login(userName, password);
        if (user.isPresent()) {
            session.setAttribute(ServletProperties.USER, user.get());
            log.info("User = {} signed in", userName);
        } else {
            log.info("User = {} not found!", userName);
            session.setAttribute(
                    ServletProperties.EXCEPTION_MESSAGE,
                    ExceptionMessage.INVALID_USER_NAME_OR_PASSWORD);
        }
        res.sendRedirect(PageName.HOME);
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
