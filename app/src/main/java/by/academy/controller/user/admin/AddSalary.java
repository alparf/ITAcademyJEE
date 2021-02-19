package by.academy.controller.user.admin;

import by.academy.constant.ExceptionMessage;
import by.academy.constant.PageName;
import by.academy.constant.ServletProperties;
import by.academy.controller.JsonController;
import by.academy.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/AddSalary")
public class AddSalary extends JsonController {
    private static final Logger log = LoggerFactory.getLogger(AddSalary.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Map<String, String> props = getRequestParameters(req);
            if (null != props) {
                long coachId = Long.parseLong(props.get(ServletProperties.COACH_ID), 10);
                double salary = Double.parseDouble(props.get(ServletProperties.SALARY));
                UserFacade.addSalary(coachId, salary)
                        .ifPresent(s -> log.info("Add salary = {} to Coach(" +
                                s.getCoach().getUserName() + ")", s.getValue()));
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            req.getSession().setAttribute(
                    ServletProperties.EXCEPTION_MESSAGE,
                    ExceptionMessage.VALUE_HAVE_TO_BE_NUMBER);
        }
        resp.sendRedirect(PageName.HOME);
    }
}
