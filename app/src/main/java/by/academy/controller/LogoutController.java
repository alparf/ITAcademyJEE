package by.academy.controller;

import by.academy.constant.JSPConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutController")
public class LogoutController extends AbstractController{

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        res.sendRedirect(JSPConstant.LOGIN);
    }
}
