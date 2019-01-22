package com.mystore.data;

import com.mystore.entity.Product;
import com.mystore.servlet.ShopItems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple storage for products
 */
public class Storage {
    private static Logger log = Logger.getLogger(ShopItems.class.getName());

    private static final String DEFAULT_SEPARATOR = ",";
    private static final String WEBAPPS_WEB_INF_CLASSES_PATH = "/webapps/ROOT/WEB-INF/classes/";
    private static final String DATA_CSV_PATH = "/data/data.csv";
    private static final ArrayList<Product> products = new ArrayList();

    public static void init() {

        products.addAll(getProductsFromData());
    }

    public static void update() {

        if (products.isEmpty()) {

            log.info("Products list is empty");
        }
        else {

            ArrayList<Product> localStorage = getProductsFromData();
            products.clear();
            products.addAll(localStorage);
            log.info("Products successfully updated");
        }
    }

    private static ArrayList<Product> getProductsFromData() {
        ArrayList<Product> localStorage = new ArrayList();
        ArrayList<String> lines = new ArrayList();

        try {

            lines = (ArrayList<String>) Files.readAllLines(Paths.get(Objects.requireNonNull(Storage.class.getClassLoader().getResource("")).getPath()
                    .replace(WEBAPPS_WEB_INF_CLASSES_PATH, DATA_CSV_PATH)));
        }
        catch (IOException e) {

            log.log(Level.SEVERE, "Exception by parse data to products collection");
            e.printStackTrace();
        }

        lines.forEach( line -> {
            String[] splitedLine = line.split(DEFAULT_SEPARATOR);
            Product product = new Product();

            product.setId(splitedLine[0]);
            product.setName(splitedLine[1]);
            product.setPrice(Integer.valueOf(splitedLine[2]));

            localStorage.add(product);
        });

        return localStorage;
    }

    public static ArrayList<Product> getProducts() {

        return products;
    }
}
