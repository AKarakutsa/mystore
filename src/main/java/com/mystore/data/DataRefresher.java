package com.mystore.data;

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
 * from /webapp/data/data.txt
 * to   /tomcat/data/data.csv
 */
public class DataRefresher implements Runnable {

    private static Logger log = Logger.getLogger(DataRefresher.class.getName());

    private static final String WEB_INF_CLASSES_PATH = "/WEB-INF/classes/";
    private static final String WEBAPPS_WEB_INF_CLASSES_PATH = "/webapps/ROOT/WEB-INF/classes/";
    private static final String DATA_CSV_PATH = "/data/data.csv";
    private static final String DATA_TXT_PATH = "/data/data.txt";

    @Override
    public void run() {
        String appPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        String dataTxtPath = appPath.replace(WEB_INF_CLASSES_PATH, DATA_TXT_PATH);
        String dataCsvPath = appPath.replace(WEBAPPS_WEB_INF_CLASSES_PATH, DATA_CSV_PATH);

        try {

            log.info("Start update data");
            List lines = Files.readAllLines(Paths.get(dataTxtPath));
            Path csvPath = Paths.get(dataCsvPath);

            Collections.shuffle(lines);

            if (!Files.exists(csvPath.getParent())) {

                Files.createDirectories(csvPath.getParent());
                Files.write(csvPath, lines.subList(0,15));
                log.info("Data successfully updated");
            }


            log.info("Start update products");
            Storage.update();
        }
        catch (IOException e) {

            log.log(Level.SEVERE, "Exception by update data");
            e.printStackTrace();
        }
    }
}
