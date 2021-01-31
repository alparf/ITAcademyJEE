package by.academy.controller.user.admin;

import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.controller.JsonController;
import by.academy.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/CoachAddSalaryController")
public class CoachAddSalaryController extends JsonController {

    private static final Logger log = LoggerFactory.getLogger(CoachAddSalaryController.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Map<String, String> props = getRequestParameters(req);
            long coachId = Long.parseLong(props.get(ServletConstant.COACH_ID), 10);
            double salary = Double.parseDouble(props.get(ServletConstant.SALARY));
            UserFacade.addSalary(coachId, salaryFormat(salary));
            log.info("Add salary = {} to Coach(" + coachId + ")", salary);
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
        }
        resp.sendRedirect(PageConstant.HOME);
    }

    private int salaryFormat(Double salary) {
        return (int) (salary * 100);
    }
}
