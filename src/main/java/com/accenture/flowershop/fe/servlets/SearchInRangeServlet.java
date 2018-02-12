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
        Pattern RANGE_INPUT_PAT = Pattern.compile("from [1-9]{1}[0-9]*(.[0-9]{1,}){0,1} to [1-9]{1}[0-9]*(.[0-9]{1,}){0,1}");
        Matcher m = RANGE_INPUT_PAT.matcher(rangeInput);
        if(!m.matches()) { // handle this error
            HttpSession session = req.getSession();
            session.setAttribute("flowerlist", new ArrayList<Flower>());
            req.setAttribute("error", "You have entered a search query in a bad pattern, should be: \"From a to b\" where a and b are numbers");
            System.out.println("First error");
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
            return;
        }

        Pattern numberPattern = Pattern.compile("[1-9]{1}[0-9]*(.[0-9]{1,}){0,1}");
        Matcher numberMatcher = numberPattern.matcher(rangeInput);
        numberMatcher.find();
        String aStr = numberMatcher.group();
        numberMatcher.find();
        String bStr = numberMatcher.group();
        BigDecimal a = new BigDecimal(aStr).setScale(2);
        BigDecimal b = new BigDecimal(bStr).setScale(2);
        if(a.compareTo(b) > 0) { // handle this error
            HttpSession session = req.getSession();
            session.setAttribute("flowerlist", new ArrayList<Flower>());
            req.setAttribute("error", "You have entered a search query in which a > b, a must be <= b");
            req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
            return;
        }

        List<Flower> flowerlist = flowerBusinessService.getFlowersWithPriceBounds(a, b);
        HttpSession session = req.getSession();
        List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");
        for(Flower flower : flowerlist) {
            for(CartFlower cartFlower : cartlist) {
                if(flower.getName().equals(cartFlower.getName())) {
                    flower.setCount(flower.getCount() - cartFlower.getHowmany());
                }
            }
        }
        session.setAttribute("flowerlist", flowerlist);
        req.getRequestDispatcher("mainpage.jsp").forward(req, resp);
    }
}
