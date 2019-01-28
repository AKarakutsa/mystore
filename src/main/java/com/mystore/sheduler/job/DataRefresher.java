package com.mystore.sheduler.job;

import com.mystore.data.Data;

import java.util.logging.Logger;

/**
 * Refreshing data job
 */
public class DataRefresher implements Runnable {

    private static Logger LOGGER = Logger.getLogger(DataRefresher.class.getName());

    @Override
    public void run() {

        LOGGER.info("Refreshing data job started...");
        Data.update();
        LOGGER.info("... data refreshing finished");
    }
}
