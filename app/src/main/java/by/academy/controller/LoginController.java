package by.academy.controller;

import by.academy.dao.impl.UserInMemory;
import by.academy.exception.UserNotFoundException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import static by.academy.constant.FileConstant.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.academy.constant.SessionConstant.*;

public class LoginController extends AbstractController {

    private static volatile User admin;

    private static User getAdmin(String userName, String password) {
        User user = admin;
        if(user == null) {
            synchronized (LoginController.class) {
                user = admin;
                if(user == null) {
                    user = admin = new User();
                    admin.setUserName(userName);
                    admin.setPassword(password);
                }
            }
        }
        return user;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(null == admin) {
            initAdmin();
        }
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

    private void initAdmin() {
        String adminName = getServletConfig().getInitParameter(ADMIN_NAME);
        String adminPassword = getServletConfig().getInitParameter(ADMIN_PASSWORD);
        UserService service = new UserService();
        getAdmin(adminName, adminPassword);
        service.addUser(admin);
    }
}
