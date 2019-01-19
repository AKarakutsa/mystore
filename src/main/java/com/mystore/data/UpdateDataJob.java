package com.mystore.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateDataJob implements Runnable {

    private static Logger log = Logger.getLogger(UpdateDataJob.class.getName());

    private static final String WEB_INF_CLASSES_PATH = "/WEB-INF/classes/";
    private static final String DATA_CSV_PATH = "/data/data.csv";
    private static final String DATA_TXT_PATH = "/data/data.txt";

    @Override
    public void run() {
        String appPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        String dataTxtPath = appPath.replace(WEB_INF_CLASSES_PATH, DATA_TXT_PATH);
        String dataCsvPath = appPath.replace(WEB_INF_CLASSES_PATH, DATA_CSV_PATH);

        try {

            List lines = Files.readAllLines(Paths.get(dataTxtPath));

            Collections.shuffle(lines);

            Files.write(Paths.get(dataCsvPath), lines.subList(0,15));
            log.info("Data successfully updated");
        }
        catch (IOException e) {

            log.log(Level.SEVERE, "Exception by update data");
            e.printStackTrace();
        }
    }
}
