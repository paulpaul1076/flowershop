package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.FlowerBusinessService;
import com.accenture.flowershop.be.business.OrderBusinessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.fe.dto.CartFlower;
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
import java.util.List;

@WebServlet("/placeOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userdto = (UserDTO)session.getAttribute("userdto");
        BigDecimal total = (BigDecimal)session.getAttribute("total");
        System.out.println("Userdto = " + userdto);
        Order order = new Order(userdto.getLogin(), total);
        System.out.println("order business service = " + orderBusinessService);
        orderBusinessService.createOrder(order);
        List<Flower> flowerlist = flowerBusinessService.getAllFlowers();
        List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");
        for(CartFlower cartFlower : cartlist) {
            for(Flower flower : flowerlist) {
                if(cartFlower.getName().equals(flower.getName())) {
                    flower.setCount(flower.getCount() - cartFlower.getHowmany());
                    flowerBusinessService.updateFlower(flower);
                }
            }
        }
        cartlist.clear();
        BigDecimal newTotal = BigDecimal.valueOf(0);
        session.setAttribute("total", newTotal);
        List<Order> orderlist = (List<Order>)session.getAttribute("orderlist");
        orderlist.add(order);
        session.removeAttribute("placeOrderButton");
        resp.sendRedirect("mainpage.jsp");
    }
}
