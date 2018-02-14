package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.FlowerBusinessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.CartFlower;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LogoutServlet.class);
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
        // add flower counts back to database from cart
        List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");
        for(CartFlower cartFlower : cartlist) {
            //always returns one, since unique
            Flower flower = flowerBusinessService.getFlowersByName(cartFlower.getName()).get(0);
            flower.setCount(flower.getCount() + cartFlower.getHowmany());
            flowerBusinessService.updateFlower(flower);
        }
        //end
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
