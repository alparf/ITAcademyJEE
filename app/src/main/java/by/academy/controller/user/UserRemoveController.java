package by.academy.controller.user;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.controller.AbstractController;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserRemoveController")
public class UserRemoveController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(UserRemoveController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, null);
        User user = (User) session.getAttribute(SessionConstant.USER);
        if(null != user && user.getUserType() == UserType.ADMIN) {
            String userName = req.getParameter(SessionConstant.USER_NAME_TO_REMOVE);
            UserFacade.removeUser(userName);
            log.info("Remove user = {}", userName);
        }
        res.sendRedirect(ServletConstant.HOME);
    }
}
