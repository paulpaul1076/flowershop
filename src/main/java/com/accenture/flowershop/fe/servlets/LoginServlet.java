package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.FlowerBusinessService;
import com.accenture.flowershop.be.business.OrderBusinessService;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.CartFlower;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

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
        List<Flower> allFlowers = flowerBusinessService.getAllFlowers();
        List<CartFlower> cartlist = new ArrayList<>();
        List<Order> orderlist = orderBusinessService.getAllCustomersOrders(user.getLogin());
        if(user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("flowerlist", allFlowers);
            session.setAttribute("cartlist", cartlist);
            session.setAttribute("orderlist", orderlist);
            session.setAttribute("userdto", new UserDTO(user));
            session.setAttribute("total", new BigDecimal("0").setScale(2));
            session.setAttribute("discount", user.getDiscount());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("invalidLoginError.jsp");
        }
    }
}
