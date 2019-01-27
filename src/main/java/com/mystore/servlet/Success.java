package com.mystore.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet({"/shop/success", "/shop/success/"})
public class Success extends HttpServlet {

    private static Logger LOGGER = Logger.getLogger(Success.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("Obtained GET request " + req.getRequestURI());

        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/shop/success.html");

        requestDispatcher.include(req,resp);
    }
}
