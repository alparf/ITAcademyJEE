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
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = res.getWriter();
        printWriter.write(mapper.writeValueAsString(UserFacade.getAverageSalaries(monthCount)));
        printWriter.flush();
        log.info("Response as json AverageSalaries");
    }
}
