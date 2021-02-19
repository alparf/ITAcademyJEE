package by.academy.controller;

import by.academy.constant.ServletProperties;
import by.academy.facade.CoachFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AverageSalaries")
public class AverageSalaries extends JsonController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String monthCountStr = req.getParameter(ServletProperties.MONTH_COUNT);
        int monthCount = 1;
        if (null != monthCountStr) {
            monthCount = Integer.parseInt(monthCountStr);
        }
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(mapper.writeValueAsString(CoachFacade.getAverageSalaries(monthCount)));
        printWriter.flush();
    }
}
