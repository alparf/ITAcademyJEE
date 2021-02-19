package by.academy.controller.user.admin;

import by.academy.constant.ExceptionMessage;
import by.academy.constant.PageName;
import by.academy.constant.ServletProperties;
import by.academy.controller.JsonController;
import by.academy.exception.UserServiceException;
import by.academy.facade.UserFacade;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/UserController")
public class UserController extends JsonController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        String fio = req.getParameter(ServletProperties.FIO);
        String userName = req.getParameter(ServletProperties.USER_NAME);
        String password = req.getParameter(ServletProperties.PASSWORD);
        int age = Integer.parseInt(req.getParameter(ServletProperties.AGE));
        UserType userType = UserType.valueOf(req.getParameter(ServletProperties.USER_TYPE));
        synchronized (UserController.class) {
            try {
                UserFacade.addUser(User.newBuilder()
                        .withFio(fio)
                        .withAge(age)
                        .withUserName(userName)
                        .withPassword(password)
                        .withUserType(userType)
                        .build())
                        .ifPresent(user -> log.info("New User = {}", user.getUserName()));
            } catch (UserServiceException e) {
                log.error(e.getMessage(), e);
                session.setAttribute(
                        ServletProperties.EXCEPTION_MESSAGE,
                        ExceptionMessage.USER_NAME_ALREADY_USED);
            }
        }
        res.sendRedirect(PageName.HOME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(mapper.writeValueAsString(UserFacade.getAllUsers()));
        printWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String, String> props = getRequestParameters(req);
        long userId = Long.parseLong(props.get(ServletProperties.USER_ID_TO_REMOVE));
        UserFacade.removeUserById(userId)
                .ifPresent(user -> log.info("Removed user, userId = {}", user.getId()));
        res.sendRedirect(PageName.HOME);
    }
}
