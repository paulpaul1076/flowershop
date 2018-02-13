package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.OrderBusinessService;
import com.accenture.flowershop.be.entity.Order;
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

@WebServlet("/closeServlet")
public class CloseServlet extends HttpServlet{
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderidStr = req.getParameter("orderid");
        Integer orderid = Integer.parseInt(req.getParameter("orderid"));
        orderBusinessService.closeOrder(orderid);
        HttpSession session = req.getSession();
        resp.sendRedirect("admin.jsp");
    }
}
