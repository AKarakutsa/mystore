package com.mystore.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.dto.OrderDto;
import com.mystore.entity.Order;
import com.mystore.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Return order for basket
 */
@WebServlet({"/shop/basket", "/shop/basket/"})
public class Basket extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(Basket.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("Obtained GET request " + req.getRequestURI());

        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/shop/basket.html");

        requestDispatcher.include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        LOGGER.info("Obtained POST request " + req.getRequestURI());

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String ids = req.getParameter("ids");

        if (ids == null || ids.trim().isEmpty()) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else {

            Order order = OrderService.createOrder(req.getParameter("ids"));

            String json = new ObjectMapper().writeValueAsString(OrderDto.build(order));

            resp.getWriter().write(json);
        }
    }
}