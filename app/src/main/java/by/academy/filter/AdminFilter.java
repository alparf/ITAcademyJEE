package by.academy.filter;

import by.academy.constant.PageConstant;
import by.academy.constant.ServletConstant;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/UserAddController", "/UserRemoveController", "/CoachAddSalary"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User user;
        if ((null != session) && (Objects.nonNull(user = (User) session.getAttribute(ServletConstant.USER)))) {
            if (user.getUserType() == UserType.ADMIN) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect(PageConstant.HOME);
            }
        } else {
            httpServletResponse.sendRedirect(PageConstant.LOGIN);
        }
    }
}
