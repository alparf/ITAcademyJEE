package by.academy.controller;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserRemoveController")
public class UserRemoveController extends AbstractController {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, null);
        User user = (User) session.getAttribute(SessionConstant.USER);
        if(null != user && user.getUserType() == UserType.ADMIN) {
            String userName = req.getParameter(SessionConstant.USER_NAME);
            IUserService service = new UserService();
            service.removeUser(userName);
        }
        res.sendRedirect(ServletConstant.HOME);
    }
}
