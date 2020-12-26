package by.academy.controller.user;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.controller.AbstractController;
import by.academy.facade.UserFacade;

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
        UserFacade.addSalary(coachName, salaryFormat(salary));
        res.sendRedirect(ServletConstant.HOME);
    }

    private int salaryFormat(Double salary) {
        return (int) (salary * 100);
    }
}
