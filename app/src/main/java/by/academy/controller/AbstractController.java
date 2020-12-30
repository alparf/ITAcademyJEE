package by.academy.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class AbstractController extends HttpServlet {

    @Override
    abstract public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
