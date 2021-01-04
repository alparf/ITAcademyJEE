package by.academy.controller.user.admin;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.dao.impl.UserInMemory;
import by.academy.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CoachAddSalary")
public class CoachAddSalary extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UserInMemory.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String coachName = req.getParameter(SessionConstant.COACH_NAME);
        try {
            double salary = Double.parseDouble(req.getParameter(SessionConstant.SALARY));
            UserFacade.addSalary(coachName, salaryFormat(salary));
            log.info(coachName + " add salary = {}", salary);
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(ServletConstant.HOME_CONTROLLER);
        dispatcher.forward(req, res);
    }

    private int salaryFormat(Double salary) {
        return (int) (salary * 100);
    }
}
