package by.academy.controller.user.admin;

import by.academy.constant.PageName;
import by.academy.constant.ServletProperties;
import by.academy.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RemoveUser")
public class RemoveUser extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(RemoveUser.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        long userId = Long.parseLong(req.getParameter(ServletProperties.USER_ID_TO_REMOVE));
        if (UserFacade.removeUserById(userId)) {
            log.info("Removed user, userId = {}", userId);
        }
        res.sendRedirect(PageName.HOME);
    }
}
