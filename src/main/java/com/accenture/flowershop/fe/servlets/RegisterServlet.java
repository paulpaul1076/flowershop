package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessServiceImpl;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Autowired
    private UserBusinessServiceImpl userBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = userBusinessService.register(req.getParameter("login"), req.getParameter("password"), req.getParameter("name"));
        if(user != null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("invalidLoginPage.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
