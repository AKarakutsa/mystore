package com.mystore.data;

import com.mystore.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Simple storage for products
 */
public class Storage {
    private static Logger LOGGER = Logger.getLogger(Storage.class.getName());

    private static final String COMMA = ",";
    private static final String DATA_CSV_DIR = "data";
    private static final String DATA_CSV = "data.csv";
    private static final ArrayList<Product> products = new ArrayList();

    public static void create() {

        LOGGER.info("Start create data");
        List<String> dataCsv = Data.init();
        LOGGER.info("Data.csv successfully created");

        LOGGER.info("Start adding products");
        products.addAll(convertDataToProducts(dataCsv));
        LOGGER.info("Added [" + products.size() + "] products");
    }

    public static void update() {

        if (products.isEmpty()) {

            LOGGER.info("Products is empty. Nothing to update.");
        }
        else {

            LOGGER.info("Start update products");
            products.clear();
            products.addAll(convertDataToProducts(Data.getData()));
            LOGGER.info("Products successfully updated");
        }
    }

    public static List<Product> getProducts() {

        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    private static List<Product> convertDataToProducts(List<String> dataCsv) {

        List<Product> localStorage = new ArrayList<>();

        dataCsv.forEach( line -> {
            String[] splitedLine = line.split(COMMA);
            Product product = new Product();

            product.setId(splitedLine[0]);
            product.setName(splitedLine[1]);
            product.setPrice(Integer.valueOf(splitedLine[2]));

            localStorage.add(product);
        });

        return localStorage;
    }
}
