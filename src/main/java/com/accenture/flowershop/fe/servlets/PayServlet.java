package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.BusinessLogicException;
import com.accenture.flowershop.be.business.OrderBusinessService;
import com.accenture.flowershop.be.business.OrderBusinessServiceImpl;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.UserDTO;
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
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@WebServlet("/payServlet")
public class PayServlet extends HttpServlet {
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String orderidStr = req.getParameter("orderid");
        System.out.println("orderidStr = " + orderidStr);
        Integer orderid = Integer.parseInt(req.getParameter("orderid"));
        UserDTO userdto = (UserDTO)session.getAttribute("userdto");

        try {
            orderBusinessService.payOrder(userdto.getLogin(), orderid);
            resp.sendRedirect("mainpage.jsp");
        } catch (BusinessLogicException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        }
    }
}
