package com.accenture.flowershop.fe.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            session.removeAttribute(e.nextElement());
        }
        session.invalidate();

        LOG.debug("Logged out and invalidated session");
        //could you forward here through req instead of redirecting?
        resp.sendRedirect("login.jsp");
    }
}
