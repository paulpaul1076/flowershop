package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.User;
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
import java.util.List;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userBusinessService.login(req.getParameter("login"), req.getParameter("password"));
        List<Flower> allFlowers = userBusinessService.getAllFlowers();
        LOG.debug("FLOWER COUNT = {}", allFlowers.size());
        if(user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("flowerlist", allFlowers);
            req.setAttribute("userdto", new UserDTO(user));
            //req.setAttribute("flowerlist", allFlowers);
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("invalidLoginError.jsp");
        }
    }
}
