package by.academy.controller;

import static by.academy.constant.FileConstant.*;

import by.academy.constant.FileConstant;
import by.academy.constant.SessionConstant;
import by.academy.model.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class HomeController extends AbstractController {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionConstant.USER);
        RequestDispatcher dispatcher;
        String path = WEB_INF + File.separator + VIEWS + File.separator;
        if(user == null) {
            dispatcher = req.getRequestDispatcher(path + LOGIN);
        } else {
            dispatcher = req.getRequestDispatcher(path + HOME);
        }
        dispatcher.forward(req, res);
    }
}
