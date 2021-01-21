package by.academy.controller.user.admin;

import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
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

@WebServlet("/CoachAddSalaryController")
public class CoachAddSalaryController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(CoachAddSalaryController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            long coachId = Long.parseLong(req.getParameter(ServletConstant.COACH_ID));
            double salary = Double.parseDouble(req.getParameter(ServletConstant.SALARY));
            UserFacade.addSalary(coachId, salaryFormat(salary));
            log.info("Add salary = {} to Coach(" + coachId + ")", salary);
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
        }
        res.sendRedirect(PageConstant.HOME);
    }

    private int salaryFormat(Double salary) {
        return (int) (salary * 100);
    }
}
