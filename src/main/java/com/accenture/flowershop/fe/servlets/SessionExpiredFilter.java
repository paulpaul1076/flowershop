package com.accenture.flowershop.fe.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionExpiredFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String pagename = request.getParameter("pagename");
        HttpSession session = request.getSession();
        String url = ((HttpServletRequest)request).getRequestURL().toString();
        String queryString = ((HttpServletRequest)request).getQueryString();
        System.out.println(url);
        if((session == null || session.getAttribute("userdto") == null) &&
                !url.equals("http://localhost:8080/") &&
                !url.equals("http://localhost:8080/loginServlet") &&
                !url.equals("http://localhost:8080/registerServlet") &&
                !url.equals("http://localhost:8080/login.jsp") &&
                !url.equals("http://localhost:8080/register.jsp") &&
                !url.equals("http://localhost:8080/ws/flowersStockWebService") &&
                !url.equals("http://localhost:8080/ws")) {

            response.sendRedirect("http://localhost:8080/");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
