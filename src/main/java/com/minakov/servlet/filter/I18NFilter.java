package com.minakov.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter sets a session attribute 'locale' to
 * manipulate with i18n GUI
 *
 * @author Serhii Minakov
 */
public class I18NFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if(session.getAttribute("locale")==null||req.getParameter("rus")!=null){
            session.setAttribute("locale", "ru");
        }
        if(req.getParameter("eng")!=null){
            session.setAttribute("locale", "en");
        }


        filterChain.doFilter(servletRequest, servletResponse);

    }

}
