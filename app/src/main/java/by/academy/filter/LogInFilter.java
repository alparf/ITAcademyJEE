package by.academy.filter;

import by.academy.constant.ServletConstant;
import by.academy.constant.SessionConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/HomeController")
public class LogInFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        if((null == session) || (null == session.getAttribute(SessionConstant.USER))) {
            ((HttpServletResponse) response).sendRedirect(ServletConstant.LOGIN);
        } else {
            chain.doFilter(request, response);
        }
    }
}
