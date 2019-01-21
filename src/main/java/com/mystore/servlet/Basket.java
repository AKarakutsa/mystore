package com.mystore.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.dto.OrderDto;
import com.mystore.entity.Order;
import com.mystore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet({"/mystore/shop/basket"})
public class Basket extends HttpServlet {
    private static Logger log = Logger.getLogger(Basket.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("basket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String ids = req.getParameter("ids");

        if (ids == null || ids.trim().isEmpty()) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else {

            Order order = OrderService.createOrder(req.getParameter("ids"));

            String json = new ObjectMapper().writeValueAsString(OrderDto.build(order));

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
    }
}