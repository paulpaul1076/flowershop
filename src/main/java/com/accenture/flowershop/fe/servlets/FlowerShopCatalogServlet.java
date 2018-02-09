package com.accenture.flowershop.fe.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/catalog")
public class FlowerShopCatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger LOG = LoggerFactory.getLogger(FlowerShopCatalogServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        LOG.debug("AJAX CALLED doGet in servlet");
//        String text = "{\"leftcol\":\"testleft\", \"rightcol\":\"testright\"}";
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write(text);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
