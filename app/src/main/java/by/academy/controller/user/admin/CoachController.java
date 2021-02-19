package by.academy.controller.user.admin;

import by.academy.controller.JsonController;
import by.academy.facade.CoachFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CoachController")
public class CoachController extends JsonController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(mapper.writeValueAsString(CoachFacade.getAllCoaches()));
        printWriter.flush();
    }
}
