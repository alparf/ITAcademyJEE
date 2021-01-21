package by.academy.controller.user.admin;

import by.academy.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserListController")
public class UserListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(mapper.writeValueAsString(UserFacade.getAllUsers()));
        printWriter.flush();
    }
}
