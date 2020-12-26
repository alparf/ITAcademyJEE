package by.academy.controller.user;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.controller.AbstractController;
import by.academy.model.factory.CoachFactory;
import by.academy.service.ICoachService;
import by.academy.service.IUserService;
import by.academy.service.impl.CoachService;
import by.academy.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CoachAddSalary")
public class CoachAddSalary extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String coachName = req.getParameter(SessionConstant.COACH_NAME);
        double salary = Double.parseDouble(req.getParameter(SessionConstant.SALARY));
        ICoachService coachService = new CoachService();
        IUserService userService = new UserService();
        coachService.addSalary(CoachFactory.createCoach(userService.getUser(coachName)), salaryFormat(salary));
        res.sendRedirect(ServletConstant.HOME);
    }

    private int salaryFormat(Double salary) {
        return (int) (salary * 100);
    }
}
