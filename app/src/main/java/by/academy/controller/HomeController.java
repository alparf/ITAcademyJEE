package by.academy.controller;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.model.bean.User;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/HomeController")
public class HomeController extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionConstant.USER);
        if(user == null) {
            res.sendRedirect(ServletConstant.LOGIN);
        }
        switch (user.getUserType()) {
            case ADMIN: {
                IUserService service = new UserService();
                session.setAttribute(SessionConstant.USER_LIST, service.getUsers());
                break;
            }
            case STUDENT: {
                break;
            }
        }
        res.sendRedirect(ServletConstant.HOME);
    }
}
