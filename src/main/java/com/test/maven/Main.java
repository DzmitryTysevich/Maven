package com.test.maven;

import com.test.maven.service.FileService;
import com.test.maven.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger("com.test.maven");
    private static final String PATH1 = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs/old_file1.json";
    private static final String PATH2 = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs/old_file2.json";
    private static final String CONFIGS_PATH = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs";

    public static String getConfigsPath() {
        return CONFIGS_PATH;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void main(String[] args) {
        LOGGER.trace("Start trace");
        LOGGER.info("Entering application.");
        Properties properties = PropertiesUtil.SINGLETON.getProperties();
        String suffix = properties.getProperty("suffix");
        String path = properties.getProperty("path");

        File oldFile1 = new File(PATH1);
        File oldFile2 = new File(PATH2);

        FileService.SINGLETON.checkOldFile1(properties, oldFile1);
        FileService.SINGLETON.renameOldFile1(suffix, path, oldFile1);

        FileService.SINGLETON.checkOldFile2(properties, oldFile2);
        FileService.SINGLETON.renameOldFile2(suffix, path, oldFile2);

        File files = new File(CONFIGS_PATH);
        LOGGER.info(Arrays.toString(files.list()));

        XmlResultFile xmlResultFile = new XmlResultFile();
        xmlResultFile.writeXML();
        LOGGER.info("Result created to XML");
        LOGGER.info("Application closed.");
        LOGGER.trace("End trace");
    }
}