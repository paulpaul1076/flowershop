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
import java.util.List;

@WebServlet("/showAllFlowersServlet")
public class ShowAllFlowersServlet extends HttpServlet {
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*List<Flower> flowerlist = flowerBusinessService.getAllFlowers();
        HttpSession session = req.getSession();
        List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");
        for(Flower flower : flowerlist) {
            for(CartFlower cartFlower : cartlist) {
                if(flower.getName().equals(cartFlower.getName())) {
                    flower.setCount(flower.getCount() - cartFlower.getHowmany());
                }
            }
        }
        session.setAttribute("newflowerlist", flowerlist);*/
        //page loads all flowers by default
        resp.sendRedirect("mainpage.jsp");
    }
}
