package by.academy.controller.user.admin;

import by.academy.facade.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserListController")
public class UserListController extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter printWriter = res.getWriter();
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/json");
        printWriter.write(mapper.writeValueAsString(UserFacade.getAllUsers()));
        printWriter.flush();
    }
}
