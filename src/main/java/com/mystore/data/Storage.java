package com.mystore.data;

import com.mystore.entity.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
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

    public static void init() {

        products.addAll(getProductsFromData());
    }

    public static void update() {

        if (products.isEmpty()) {

            LOGGER.info("Products list is empty");
        }
        else {

            ArrayList<Product> localStorage = getProductsFromData();
            products.clear();
            products.addAll(localStorage);
            LOGGER.info("Products successfully updated");
        }
    }

    private static ArrayList<Product> getProductsFromData() {
        ArrayList<Product> localStorage = new ArrayList();
        ArrayList<String> lines = new ArrayList();

        try {
            String sharedLoaderPath = System.getProperty("shared.loader");
            String dataCsvPath = String.join(File.separator, sharedLoaderPath, DATA_CSV_DIR, DATA_CSV);

            LOGGER.info("Try read data from " + dataCsvPath);

            lines = (ArrayList<String>) Files.readAllLines(Paths.get(dataCsvPath));
        }
        catch (IOException e) {

            LOGGER.log(Level.SEVERE, "Exception by parse data to products collection", e);
        }

        lines.forEach( line -> {
            String[] splitedLine = line.split(COMMA);
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
