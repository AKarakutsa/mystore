package com.mystore.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Refreshing data
 * from System.getProperty("shared.loader")data.txt
 * to   System.getProperty("shared.loader")data/data.csv
 */
public class DataRefresher implements Runnable {

    private static Logger LOGGER = Logger.getLogger(DataRefresher.class.getName());

    private static final String DATA_CSV_DIR = "data";
    private static final String DATA_CSV = "data.csv";
    private static final String DATA_TXT = "data.txt";

    @Override
    public void run() {
        String sharedLoaderPath = System.getProperty("shared.loader");
        String dataTxtPath = String.join(File.separator, sharedLoaderPath, DATA_TXT);
        String dataCsvPath = String.join(File.separator, sharedLoaderPath, DATA_CSV_DIR, DATA_CSV);

        LOGGER.info("Start update data");
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
            Files.write(csvPath, lines.subList(0,15));
            LOGGER.info("Data successfully updated");

            LOGGER.info("Start update products");
            Storage.update();
        }
        catch (IOException e) {

            LOGGER.log(Level.SEVERE, "Exception by update data", e);
        }
    }
}
