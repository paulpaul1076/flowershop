package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.BusinessLogicException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/searchInRangeServlet")
public class SearchInRangeServlet extends HttpServlet{
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rangeInput = req.getParameter("searchInRange");
        List<BigDecimal> bounds = null;
        try{
            bounds = flowerBusinessService.validateBoundInput(rangeInput);
        } catch (BusinessLogicException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
            return;
        }
        //validation logic end
        List<Flower> flowerlist = flowerBusinessService.getFlowersWithPriceBounds(bounds.get(0), bounds.get(1));
        HttpSession session = req.getSession();

        session.setAttribute("newflowerlist", flowerlist);
        resp.sendRedirect("mainpage.jsp");
    }
}
