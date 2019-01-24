package com.mystore.servlet;

import com.mystore.entity.Order;
import com.mystore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Create validate and save order
 */
@WebServlet({"/buyService"})
public class BuyService extends HttpServlet {

    private static Logger log = Logger.getLogger(BuyService.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Obtained POST request " + req.getRequestURI());

        String ids = req.getParameter("ids");

        if (ids == null || ids.trim().isEmpty()) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else {

            Order order = OrderService.createOrder(req.getParameter("ids"));

            if (OrderService.orderValid(order)) {

                if (OrderService.saveOrder(order)) {
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                }
                else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
            else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
    }
}