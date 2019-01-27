package com.mystore.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.data.Storage;
import com.mystore.dto.ProductDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Return available products
 */
@WebServlet({"/shop/items"})
public class ShopItems extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(ShopItems.class.getName());

    @Override
    public void init() throws ServletException {
        Storage.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        LOGGER.info("Obtained POST request " + req.getRequestURI());

        String json = new ObjectMapper().writeValueAsString(Storage.getProducts().stream()
                .map(ProductDto::build)
                .collect(Collectors.toList()));

        LOGGER.info("Products = " + json);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().write(json);
    }
}