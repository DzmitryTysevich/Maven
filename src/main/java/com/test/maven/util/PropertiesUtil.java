package com.test.maven.util;

import com.test.maven.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class PropertiesUtil {
    public static PropertiesUtil SINGLETON = new PropertiesUtil();
    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        LOGGER.info("Properties have been gotten");
        try {
            properties.loadFromXML(Main.class.getResourceAsStream("/config.xml"));
            LOGGER.info("File config.properties have been read");
        } catch (Exception e) {
            LOGGER.error("Exception happen!", e);
        }
        return properties;
    }
}