package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessServiceImpl;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Autowired
    private UserBusinessServiceImpl userBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
        LOG.debug("userBusinessService = {}", userBusinessService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("In doPost");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("In doGet");
        LOG.debug("login = {}, password = {}", req.getParameter("login"), req.getParameter("password"));
        UserDTO user = userBusinessService.login(req.getParameter("login"), req.getParameter("password"));
        if(user != null) {
            HttpSession session = req.getSession();
            //session.setAttribute("currentSessionUser", user);
            //session.setAttribute("name", user.getName());
            //LOG.debug("");
            req.setAttribute("money", user.getBalance());
            req.setAttribute("discount", user.getDiscount());
            req.setAttribute("name", user.getName());
            // how to add attributes to response???
            //resp.sendRedirect("mainpage.jsp");
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("invalidLoginPage.jsp");
        }
    }
}
