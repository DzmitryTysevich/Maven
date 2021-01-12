package com.test.maven;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.trace("Start trace");
        LOGGER.info("Entering application.");
        Properties properties = new Properties();
        LOGGER.info("Properties have been gotten");
        try {
            properties.load(Main.class.getResourceAsStream("/config.properties"));
            LOGGER.info("File config.properties have been read");
        } catch (Exception e) {
            LOGGER.error("Exception happen!", e);
        }

        File oldFile1 = new File("/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs/old_file1.json");
        File oldFile2 = new File("/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs/old_file2.json");

        String suffix = properties.getProperty("suffix");
        String path = properties.getProperty("path");

        LOGGER.info("Checking oldFile1...");
        String file1 = properties.getProperty("file1");
        if (oldFile1.canRead() && file1.equals(oldFile1.getAbsolutePath())) {
            LOGGER.info(file1 + " - " + true);
            LOGGER.info("Rename oldFile1...");
        } else {
            LOGGER.info(file1 + " - " + false);
        }
        oldFile1.renameTo(new File(path + suffix + "_file1.json"));
        LOGGER.info("oldFile1 have been renamed");

        LOGGER.info("Checking oldFile2...");
        String file2 = properties.getProperty("file2");
        if (oldFile2.canRead() && file2.equals(oldFile2.getAbsolutePath())) {
            LOGGER.info(file2 + " - " + true);
            LOGGER.info("Rename oldFile2...");

        } else {
            LOGGER.info(file2 + " - " + false);
        }
        oldFile2.renameTo(new File(path + suffix + "_file2.json"));
        LOGGER.info("oldFile2 have been renamed");

        File files = new File("/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs");
        LOGGER.info(Arrays.toString(files.list()));
        LOGGER.info("Application closed.");
        LOGGER.trace("End trace");
    }
}