package com.mystore.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {

    private static Logger LOGGER = Logger.getLogger(Data.class.getName());

    private static final String DATA_CSV_DIR = "data";
    private static final String DATA_CSV = "data.csv";
    private static final String DATA_TXT = "data.txt";

    public static List<String> init() {

        String sharedLoaderPath = System.getProperty("shared.loader");
        String dataTxtPath = String.join(File.separator, sharedLoaderPath, DATA_TXT);
        String dataCsvPath = String.join(File.separator, sharedLoaderPath, DATA_CSV_DIR, DATA_CSV);
        List<String> dataCsv = new ArrayList<>();

        try {

            LOGGER.info("Read data from " + dataTxtPath);
            List lines = Files.readAllLines(Paths.get(dataTxtPath));
            Path csvPath = Paths.get(dataCsvPath);

            Collections.shuffle(lines);

            if (!Files.exists(csvPath.getParent())) {

                LOGGER.info("Data directory " + csvPath.getParent() + " isn't exist. Try create...");
                LOGGER.info("Created directory " + Files.createDirectories(csvPath.getParent()));
            }

            LOGGER.info("Write data to " + csvPath);
            List<String> availableProducts = lines.subList(0,15);
            Files.write(csvPath, availableProducts);
            dataCsv = availableProducts;
        }
        catch (IOException e) {

            LOGGER.log(Level.SEVERE, "Exception by update data", e);
        }

        return dataCsv;
    }

    public static List<String> update() {
        return init();
    }

    public static List<String> getData() {

        String sharedLoaderPath = System.getProperty("shared.loader");
        String dataCsvPath = String.join(File.separator, sharedLoaderPath, DATA_CSV_DIR, DATA_CSV);
        List<String> dataCsv = new ArrayList<>();

        try {

            LOGGER.info("Read available products data from " + dataCsvPath);
            dataCsv = Files.readAllLines(Paths.get(dataCsvPath));
        }
        catch (IOException e) {

            LOGGER.log(Level.SEVERE, "Exception by update data", e);
        }

        return dataCsv;
    }
}
