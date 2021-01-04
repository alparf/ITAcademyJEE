package by.academy.controller;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.strategy.IUserStrategy;
import by.academy.strategy.impl.AdminStrategy;
import by.academy.strategy.impl.UserStrategy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionConstant.USER);
        IUserStrategy strategy = null;
        if(user.getUserType() == UserType.ADMIN) {
            strategy = AdminStrategy.create(session);
        }
        if(user.getUserType() == UserType.STUDENT) {
            strategy = UserStrategy.create(session);
        }
        if(null != strategy) {
            strategy.sessionInit();
        }
        res.sendRedirect(ServletConstant.HOME);
    }
}
