package by.academy.controller.user.admin;

import by.academy.constant.ExceptionConstant;
import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.exception.UserServiceException;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
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

@WebServlet("/UserAddController")
public class UserAddController extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(UserAddController.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(ServletConstant.EXCEPTION_MESSAGE, null);
        String fio = req.getParameter(ServletConstant.FIO);
        String userName = req.getParameter(ServletConstant.USER_NAME);
        String password = req.getParameter(ServletConstant.PASSWORD);
        int age = Integer.parseInt(req.getParameter(ServletConstant.AGE));
        UserType userType = UserType.valueOf(req.getParameter(ServletConstant.USER_TYPE));
        synchronized (UserAddController.class) {
            try {
                if (UserFacade.addUser(User.newBuilder()
                        .withFio(fio)
                        .withAge(age)
                        .withUserName(userName)
                        .withPassword(password)
                        .withUserType(userType)
                        .build())) {
                    log.info("New User = {}", userName);
                }
            } catch (UserServiceException e) {
                log.error(e.getMessage(), e);
                session.setAttribute(ServletConstant.EXCEPTION_MESSAGE, ExceptionConstant.USER_NAME_ALREADY_USED);
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(PageConstant.HOME_CONTROLLER);
        dispatcher.forward(req, res);
    }
}
