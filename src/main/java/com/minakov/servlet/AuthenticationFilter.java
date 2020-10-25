package com.minakov.servlet;

import com.minakov.database.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
        if(user!=null) {
            if (!"admin".equals(user.getType().getName())){
                req.getRequestDispatcher("").forward(req,resp);
            }
            filterChain.doFilter(servletRequest, servletResponse);// must have logged stack trace
        } else{
            req.getRequestDispatcher("").forward(req,resp);
        }
    }

}
