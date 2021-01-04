package by.academy.controller.user.admin;

import by.academy.constant.ExceptionConstant;
import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;
import by.academy.exception.UserServiceException;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserAddController")
public class UserAddController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(UserAddController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, null);
        User user = (User) session.getAttribute(SessionConstant.USER);
        if(null != user && user.getUserType() == UserType.ADMIN) {
            String fio = req.getParameter(SessionConstant.FIO);
            String userName = req.getParameter(SessionConstant.USER_NAME);
            String password = req.getParameter(SessionConstant.PASSWORD);
            int age = Integer.parseInt(req.getParameter(SessionConstant.AGE));
            UserType userType = UserType.valueOf(req.getParameter(SessionConstant.USER_TYPE));
            try {
                UserFacade.addUser(UserFactory.createUser(fio, age, userName, password, userType));
                log.info("New User = {}", userName);
            } catch (UserServiceException e) {
                log.error(e.getMessage(), e);
                session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, ExceptionConstant.USER_NAME_ALREADY_USED);
            }
        }
        res.sendRedirect(ServletConstant.HOME);
    }
}
