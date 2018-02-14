package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.business.UserBusinessServiceImpl;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.xml.UserMarshallingService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private UserMarshallingService userMarshallingService;
    private Logger log = LoggerFactory.getLogger(RegisterServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setLogin(req.getParameter("login"));
        user.setAddress(req.getParameter("address"));
        user.setPhone(req.getParameter("phone"));
        boolean success = userBusinessService.register(user);

        log.debug(user.toString());

        if(success) {
            String fileName = "C:\\flowershop_users\\" + user.getLogin() + ".xml";

            File file = new File(fileName);
            while(file.exists()) {
                fileName += "1";
                file = new File(fileName);
            }

            file.getParentFile().mkdirs();
            file.createNewFile();

            //try {
                userMarshallingService.marshall(user, fileName);
            //} catch (Exception e) {
            //    log.debug("exception message: {}", e.getMessage());
            //}
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("failedRegistrationError.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
