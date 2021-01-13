package by.academy.controller;

import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.strategy.IUserStrategy;
import by.academy.strategy.impl.AdminStrategy;

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
        User user = (User) session.getAttribute(ServletConstant.USER);
        IUserStrategy strategy = null;
        if ((null != user) && (user.getUserType() == UserType.ADMIN)) {
            strategy = AdminStrategy.create(req);
        }
        if (null != strategy) {
            strategy.sessionInit();
        }
        req.getRequestDispatcher(PageConstant.HOME).forward(req, res);
    }
}
