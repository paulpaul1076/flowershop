package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.BusinessLogicException;
import com.accenture.flowershop.be.business.FlowerBusinessService;
import com.accenture.flowershop.be.business.OrderBusinessService;
import com.accenture.flowershop.be.business.OrderBusinessServiceImpl;
import com.accenture.flowershop.be.entity.Flower;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {

    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flowerName = req.getParameter("name");
        String price = req.getParameter("price"); // remove this from here and jsp
        String howManyToAddStr = req.getParameter("number");

        HttpSession session = req.getSession();
        List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");
        UserDTO userdto = (UserDTO)session.getAttribute("userdto");
        CartFlower newFlower = null;
        try {
            newFlower = orderBusinessService.addToCart(userdto.getLogin(),
                    cartlist,
                    flowerName,
                    howManyToAddStr);
        } catch (BusinessLogicException e) {
            System.out.println("Yeah, there's an exception: " + e.getMessage());
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
            return;
        }

        BigDecimal prevCartTotal = (BigDecimal)session.getAttribute("total");
        session.setAttribute("placeOrderButton","<input type=\"submit\" value = \"Place order\">"); // create the place order button (make it available)

        BigDecimal newTotal = prevCartTotal.add(newFlower.getTotal());
        session.setAttribute("total", newTotal);

        //add to existing if exists in cart, redirect and return
        for(CartFlower f : cartlist) {
            if(f.getName().equals(newFlower.getName())) {
                f.setTotal(f.getTotal().add(newFlower.getTotal()));
                f.setHowmany(f.getHowmany() + newFlower.getHowmany());
                resp.sendRedirect("mainpage.jsp");
                return;
            }
        }

        cartlist.add(newFlower);
        resp.sendRedirect("mainpage.jsp");
    }
}
