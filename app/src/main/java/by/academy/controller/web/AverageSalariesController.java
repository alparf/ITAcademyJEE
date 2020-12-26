package by.academy.controller.web;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.controller.AbstractController;
import by.academy.service.impl.CoachService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AverageSalariesController")
public class AverageSalariesController extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CoachService service = new CoachService();
        String monthCountStr = req.getParameter(SessionConstant.MONTH_COUNT);
        int monthCount = 0;
        if(null != monthCountStr) {
            monthCount = Integer.parseInt(monthCountStr);
        }
        session.setAttribute(SessionConstant.MONTH_COUNT, monthCount);
        session.setAttribute(SessionConstant.COACH_LIST, service.getCoachList());
        res.sendRedirect(ServletConstant.AVERAGE);
    }
}
