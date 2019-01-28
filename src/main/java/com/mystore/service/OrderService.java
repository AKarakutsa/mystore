package com.mystore.service;

import com.mystore.data.Storage;
import com.mystore.entity.Order;
import com.mystore.entity.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OrderService {
    private static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    private static final String DEFAULT_SEPARATOR = ",";
    private static final String DATA_DIR = "data";

    /**
     * Creating order from product ids
     */
    public static Order createOrder(String ids) {
        Order order = new Order();

        if (ids != null && !ids.trim().isEmpty()) {

            Arrays.stream(ids.split(DEFAULT_SEPARATOR)).forEach(id ->
                    order.getProducts().put(id, Storage.getProducts().stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null)));

            order.setSum(order.getProducts().values().stream().filter(Objects::nonNull).mapToInt(Product::getPrice).sum());
        }

        LOGGER.info("Order = " + order.toString());

        return order;
    }

    /**
     * Validation new order for products availability
     */
    public static boolean orderValid(Order order) {
        return order.getProducts().values().stream().noneMatch(Objects::isNull);
    }

    /**
     * Saving order to .csv file
     */
    public static boolean saveOrder(Order order) {

        String webappsPath = System.getProperty("catalina.base");
        String orderPath = String.join(File.separator, webappsPath, DATA_DIR, "order-" + new Date() + ".csv");

        try {

            String data = "ID,NAME,PRICE\n";
            data += order.getProducts().values().stream()
                    .map(product -> String.join(",",product.getId(), product.getName(), product.getPrice().toString()))
                    .collect(Collectors.joining("\n"));


            Files.write(Paths.get(orderPath), data.getBytes());
            return true;
        }
        catch (IOException e) {

            LOGGER.log(Level.SEVERE, "Exception by saving order", e);
            return false;
        }
    }
}
