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

@WebServlet({"/mystore/shop/items"})
public class ShopItems extends HttpServlet {
    private static Logger log = Logger.getLogger(ShopItems.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        log.info("Obtained request for getting products");

        String json = new ObjectMapper().writeValueAsString(Storage.getProducts().stream()
                .map(ProductDto::build)
                .collect(Collectors.toList()));

        log.info("Products = " + json);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}