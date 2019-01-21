package com.mystore.service;

import com.mystore.data.Storage;
import com.mystore.entity.Order;
import com.mystore.entity.Product;
import com.mystore.servlet.ShopItems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OrderService {
    private static Logger log = Logger.getLogger(ShopItems.class.getName());

    private static final String DEFAULT_SEPARATOR = ",";
    private static final String WEB_INF_CLASSES_PATH = "/WEB-INF/classes/";
    private static final String DATA_DIR_PATH = "/data/";

    public static Order createOrder(String ids) {
        Order order = new Order();

        if (ids != null && !ids.trim().isEmpty()) {

            Arrays.stream(ids.split(DEFAULT_SEPARATOR)).forEach(id ->
                    order.getProducts().put(id, Storage.getProducts().stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null)));

            order.setSum(order.getProducts().values().stream().filter(Objects::nonNull).mapToInt(Product::getPrice).sum());
        }

        log.info("Order = " + order.toString());

        return order;
    }

    public static boolean orderValid(Order order) {
        return order.getProducts().values().stream().noneMatch(Objects::isNull);
    }

    public static boolean saveOrder(Order order) {

        String dataDirPath = Objects.requireNonNull(Storage.class.getClassLoader().getResource("")).getPath()
                .replace(WEB_INF_CLASSES_PATH, DATA_DIR_PATH + "order-" + new Date() + ".csv");

        try {

            String data = "ID,NAME,PRICE\n";
            data += order.getProducts().values().stream()
                    .map(product -> String.join(",",product.getId(), product.getName(), product.getPrice().toString()))//product.getId() + "," + product.getName() + "," +  + "\n")
                    .collect(Collectors.joining("\n"));


            Files.write(Paths.get(dataDirPath), data.getBytes());
            return true;
        }
        catch (IOException e) {

            log.log(Level.SEVERE, "Exception by saving order");
            e.printStackTrace();
            return false;
        }
    }
}
