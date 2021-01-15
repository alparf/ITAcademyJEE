package by.academy.controller;

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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AverageSalariesController")
public class AverageSalariesController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AverageSalariesController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String monthCountStr = req.getParameter(ServletConstant.MONTH_COUNT);
        int monthCount = 1;
        if (null != monthCountStr) {
            monthCount = Integer.parseInt(monthCountStr);
        }
        req.setAttribute(ServletConstant.MONTH_COUNT, monthCount);
        try {
            req.setAttribute(ServletConstant.AVERAGE_SALARIES, UserFacade.getAverageSalaries(monthCount));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = res.getWriter();
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/json");
        printWriter.write(mapper.writeValueAsString(UserFacade.getAverageSalaries(monthCount)));
    }
}
