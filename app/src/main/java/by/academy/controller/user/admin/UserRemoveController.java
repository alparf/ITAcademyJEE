package by.academy.controller.user.admin;

import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserRemoveController")
public class UserRemoveController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(UserRemoveController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(ServletConstant.EXCEPTION_MESSAGE, null);
        long userId = Long.parseLong(req.getParameter(ServletConstant.USER_ID_TO_REMOVE));
        if (UserFacade.removeUserById(userId)) {
            log.info("Removed user, userId = {}", userId);
        }
        res.sendRedirect(PageConstant.HOME);
    }
}
