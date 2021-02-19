package by.academy.controller.user;

import by.academy.constant.PageName;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        res.sendRedirect(PageName.LOGIN);
    }
}
