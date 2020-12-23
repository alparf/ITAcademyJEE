package by.academy.controller;

import by.academy.constant.ExceptionConstant;
import by.academy.constant.JSPConstant;
import by.academy.constant.SessionConstant;
import by.academy.exception.UserServiceException;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.UserFactory;
import by.academy.service.IUserService;
import by.academy.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserAddController")
public class UserAddController extends AbstractController {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionConstant.USER);
        if(null != user && user.getUserType() == UserType.ADMIN) {
            String fio = req.getParameter(SessionConstant.FIO);
            String userName = req.getParameter(SessionConstant.USER_NAME);
            String password = req.getParameter(SessionConstant.PASSWORD);
            int age = Integer.parseInt(req.getParameter(SessionConstant.AGE));
            UserType userType = UserType.valueOf(req.getParameter(SessionConstant.USER_TYPE));
            IUserService service = new UserService();
            try {
                service.addUser(UserFactory.createUser(fio, age, userName, password, userType));
                session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, null);
            } catch (UserServiceException e) {
                e.printStackTrace();
                session.setAttribute(SessionConstant.EXCEPTION_MESSAGE, ExceptionConstant.USER_NAME_ALREADY_USED);
            }
        }
        res.sendRedirect(JSPConstant.HOME);
    }
}
