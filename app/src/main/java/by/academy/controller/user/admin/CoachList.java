package by.academy.controller.user.admin;

import by.academy.controller.AbstractController;
import by.academy.controller.JsonController;
import by.academy.facade.UserFacade;
import by.academy.model.bean.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CoachList")
public class CoachList extends JsonController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(mapper.writeValueAsString(UserFacade.getAllUsers(UserType.COACH)));
        printWriter.flush();
    }
}
