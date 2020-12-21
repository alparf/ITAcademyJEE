package by.academy.controller;

import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

import static by.academy.constant.FileConstant.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static by.academy.constant.FileConstant.VIEWS;
import static by.academy.constant.FileConstant.WEB_INF;
import static by.academy.constant.SessionConstant.*;

public class LoginController extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter(USER_NAME);
        String password = req.getParameter(PASSWORD);
        User user = new User("FIO", 0, userName, password, UserType.STUDENT);
        RequestDispatcher dispatcher = req.getRequestDispatcher(
                WEB_INF + File.separator + VIEWS + File.separator + HOME);
        dispatcher.forward(req, res);
    }
}
