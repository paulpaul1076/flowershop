package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.FlowerBusinessService;
import com.accenture.flowershop.be.business.OrderBusinessService;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.CartFlower;
import com.accenture.flowershop.fe.dto.UserDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    //----
    private MapperFacade mapperFacade;
    //----

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        //-----
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserDTO.class);
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userBusinessService.login(req.getParameter("login"), req.getParameter("password"));
        List<Flower> allFlowers = new ArrayList<>();//flowerBusinessService.getAllFlowers();
        List<CartFlower> cartlist = new ArrayList<>();
        if (user != null) {
            HttpSession session = req.getSession();
            if (user.getLogin().equals("admin")) {
                List<Order> allOrders = new ArrayList<>(); // redo in jsp
                session.setAttribute("allOrders", allOrders);
                session.setAttribute("orderBusinessService", orderBusinessService);
                System.out.println("All orders : ");
                for (Order o : allOrders) {
                    System.out.println(o);
                }
                resp.sendRedirect("admin.jsp");
                return;
            }
            List<Order> orderlist = new ArrayList<>();//orderBusinessService.getAllCustomersOrders(user.getLogin());
            session.setAttribute("flowerBusinessService", flowerBusinessService);
            session.setAttribute("orderBusinessService", orderBusinessService);
            session.setAttribute("userBusinessService", userBusinessService);
            session.setAttribute("flowerlist", allFlowers);
            session.setAttribute("cartlist", cartlist);
            session.setAttribute("orderlist", orderlist);
            UserDTO userdto = mapperFacade.map(user, UserDTO.class);
            session.setAttribute("userdto", userdto);
            session.setAttribute("total", new BigDecimal("0").setScale(2));
            session.setAttribute("discount", user.getDiscount());

            resp.sendRedirect("mainpage.jsp");
        } else {
            resp.sendRedirect("invalidLoginError.jsp");
        }
    }
}
