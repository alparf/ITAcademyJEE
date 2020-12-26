package by.academy.controller.web;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.controller.AbstractController;
import by.academy.model.bean.User;
import by.academy.service.ICoachService;
import by.academy.service.IUserService;
import by.academy.service.impl.CoachService;
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
        } else {
            switch (user.getUserType()) {
                case ADMIN: {
                    IUserService userService = new UserService();
                    ICoachService coachService = new CoachService();
                    session.setAttribute(SessionConstant.USER_LIST, userService.getUsers());
                    session.setAttribute(SessionConstant.COACH_LIST, coachService.getCoachList());
                    break;
                }
                case STUDENT: {
                    break;
                }
            }
            res.sendRedirect(ServletConstant.HOME);
        }
    }
}
