package com.minakov.servlet.filter;

import com.minakov.database.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter realizes checking that the person that wants to
 * be connected to admin pages is an admin.
 *
 * @author Serhii Minakov
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void destroy(){

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null || !"admin".equals(user.getType().getName())) {
            session.invalidate();
            req.getRequestDispatcher("").forward(req,resp);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

}
