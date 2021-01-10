package by.academy.controller;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AverageSalariesController")
public class AverageSalariesController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AverageSalariesController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String monthCountStr = req.getParameter(SessionConstant.MONTH_COUNT);
        int monthCount = 1;
        if (null != monthCountStr) {
            monthCount = Integer.parseInt(monthCountStr);
        }
        session.setAttribute(SessionConstant.MONTH_COUNT, monthCount);
        try {
            session.setAttribute(SessionConstant.AVERAGE_SALARIES, UserFacade.getAverageSalaries(monthCount));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        res.sendRedirect(ServletConstant.AVERAGE);
    }
}
