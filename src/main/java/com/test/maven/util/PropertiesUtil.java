package com.test.maven.util;

import com.test.maven.Main;

import java.util.Properties;

public class PropertiesUtil {
    public static PropertiesUtil SINGLETON = new PropertiesUtil();

    private PropertiesUtil() {
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        Main.getLOGGER().info("Properties have been gotten");
        try {
            properties.loadFromXML(Main.class.getResourceAsStream("/config.xml"));
            Main.getLOGGER().info("File config.properties have been read");
        } catch (Exception e) {
            Main.getLOGGER().error("Exception happen!", e);
        }
        return properties;
    }
}