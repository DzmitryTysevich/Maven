package com.test.maven;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger("com.test.maven");
    private static String OLD_FILES;
    private static final String PATH1 = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs/old_file1.json";
    private static final String PATH2 = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs/old_file2.json";
    private static final String CONFIGS_PATH = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs";

    public static String getOldFiles() {
        return OLD_FILES;
    }

    public static String getConfigsPath() {
        return CONFIGS_PATH;
    }

    public static void main(String[] args) {
        LOGGER.trace("Start trace");
        LOGGER.info("Entering application.");
        Properties properties = new Properties();
        LOGGER.info("Properties have been gotten");
        try {
            properties.loadFromXML(Main.class.getResourceAsStream("/config.xml"));
            LOGGER.info("File config.properties have been read");
        } catch (Exception e) {
            LOGGER.error("Exception happen!", e);
        }

        File oldFile1 = new File(PATH1);
        File oldFile2 = new File(PATH2);

        String suffix = properties.getProperty("suffix");
        String path = properties.getProperty("path");

        LOGGER.info("Checking oldFile1...");
        String file1 = properties.getProperty("file1");
        if (oldFile1.canRead() && file1.equals(oldFile1.getAbsolutePath())) {
            OLD_FILES = Arrays.toString(new File(CONFIGS_PATH).list());
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

        File files = new File(CONFIGS_PATH);
        LOGGER.info(Arrays.toString(files.list()));
        LOGGER.info("Application closed.");
        LOGGER.trace("End trace");

        XmlResultFile.writeXML();
    }
}