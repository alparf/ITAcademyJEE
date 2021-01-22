package by.academy.controller.user.admin;

import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CoachAddSalaryController")
public class CoachAddSalaryController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(CoachAddSalaryController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (BufferedReader br = req.getReader()) {
            StringBuilder body = new StringBuilder();
            while (br.ready()) {
                body.append(br.readLine());
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> props = mapper.readValue(body.toString(), HashMap.class);
            long coachId = Long.parseLong(props.get(ServletConstant.COACH_ID), 10);
            double salary = Double.parseDouble(props.get(ServletConstant.SALARY));
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
