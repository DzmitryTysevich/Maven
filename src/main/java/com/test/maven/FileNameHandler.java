package com.test.maven;

import com.test.maven.service.FileService;
import com.test.maven.service.TimeService;
import com.test.maven.util.Constants;
import com.test.maven.util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class FileNameHandler {
    private static final Logger LOGGER = LogManager.getLogger(FileNameHandler.class);
    private final XmlResultFile xmlResultFile;

    public FileNameHandler(XmlResultFile xmlResultFile) {
        this.xmlResultFile = xmlResultFile;
    }

    public void handle() {
        long startTime = System.currentTimeMillis();
        LOGGER.trace("Start trace");
        LOGGER.info("Entering application.");
        Properties properties = PropertiesUtil.SINGLETON.getProperties();
        String suffix = properties.getProperty("suffix");
        String path = properties.getProperty("path");

        File oldFile1 = new File(Constants.PATH1);
        File oldFile2 = new File(Constants.PATH2);

        FileService.SINGLETON.checkOldFile1(properties, oldFile1);
        FileService.SINGLETON.renameOldFile1(suffix, path, oldFile1);

        FileService.SINGLETON.checkOldFile2(properties, oldFile2);
        FileService.SINGLETON.renameOldFile2(suffix, path, oldFile2);

        File files = new File(Constants.CONFIGS_PATH);
        LOGGER.info(Arrays.toString(files.list()));
        LOGGER.info("Application closed.");
        LOGGER.trace("End trace");
        long endTime = System.currentTimeMillis();

        long executionTime = TimeService.SINGLETON.getExecutionTime(startTime, endTime);
        xmlResultFile.writeXML(executionTime);
    }
}