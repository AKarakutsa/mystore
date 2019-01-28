package com.mystore.sheduler.job;

import com.mystore.data.Storage;

import java.util.logging.Logger;

/**
 * Refreshing storage job
 */
public class StorageRefresher implements Runnable {

    private static Logger LOGGER = Logger.getLogger(StorageRefresher.class.getName());

    @Override
    public void run() {

        LOGGER.info("Refreshing storage job started...");
        Storage.update();
        LOGGER.info("... storage refreshing finished");
    }
}
