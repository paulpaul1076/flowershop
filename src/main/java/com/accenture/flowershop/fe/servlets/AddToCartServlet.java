package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.FlowerBusinessService;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.CartFlower;
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
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String number = req.getParameter("number");
        System.out.println("Name = " + name + ", price = " + price + ", number = " + number);
        if(number.isEmpty()) {
            req.setAttribute("error", "The \"How many?\" field cannot be empty!");
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
            return;
        } else if(number.equals("0")) {
            req.setAttribute("error", "You can't order 0 of something!");
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
            return;
        }
        int howmany = Integer.parseInt(number);

        HttpSession session = req.getSession();
        List<Flower> flowerlist =  flowerBusinessService.getAllFlowers();
        List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");
        int discount = (int)session.getAttribute("discount");
        CartFlower newFlower = new CartFlower();
        BigDecimal totalPriceForFlower = null;
        int howmanyLeftInStock = 0;


        // find the data about this flower
        for(Flower f : flowerlist) {
            if(f.getName().equals(name)) {
                howmanyLeftInStock = f.getCount();
                if(howmanyLeftInStock - howmany < 0) {
                    req.setAttribute("error", "You cannot order more than what's left in stock!");
                    req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
                    return;
                }
                f.setCount(howmanyLeftInStock - howmany); // this shouldnt be transactional
                totalPriceForFlower = new BigDecimal(price)
                        .multiply(BigDecimal.valueOf(howmany))
                        .multiply(BigDecimal.valueOf((100.0 - discount)/100.0)).setScale(2);
                newFlower.setHowmany(howmany);
                newFlower.setName(f.getName());
                newFlower.setTotal(totalPriceForFlower);

                break;
            }
        }

        session.setAttribute("placeOrderButton","<input type=\"submit\" value = \"Place order\">"); // create the place order button (make it available)

        BigDecimal prevTotal = (BigDecimal)session.getAttribute("total");
        BigDecimal newTotal = prevTotal.add(totalPriceForFlower);
        session.setAttribute("total", newTotal);

        //add to existing if exists in cart
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
