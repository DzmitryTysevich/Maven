package com.test.maven.service;

import com.test.maven.Main;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class FileService {
    public static final FileService SINGLETON = new FileService();
    private String OLD_FILES;
    private final String CONFIGS_PATH = "/home/dzmitry/IdeaProjects/Maven/src/main/resources/configs";

    private FileService() {
    }

    public String getOLD_FILES() {
        return OLD_FILES;
    }

    public void renameOldFile2(String suffix, String path, File oldFile2) {
        oldFile2.renameTo(new File(path + suffix + "_file2.json"));
        Main.getLOGGER().info("oldFile2 have been renamed");
    }

    public void renameOldFile1(String suffix, String path, File oldFile1) {
        oldFile1.renameTo(new File(path + suffix + "_file1.json"));
        Main.getLOGGER().info("oldFile1 have been renamed");
    }

    public void checkOldFile2(Properties properties, File oldFile2) {
        Main.getLOGGER().info("Checking oldFile2...");
        String file2 = properties.getProperty("file2");
        if (oldFile2.canRead() && file2.equals(oldFile2.getAbsolutePath())) {
            Main.getLOGGER().info(file2 + " - " + true);
            Main.getLOGGER().info("Rename oldFile2...");

        } else {
            Main.getLOGGER().info(file2 + " - " + false);
        }
    }

    public void checkOldFile1(Properties properties, File oldFile1) {
        Main.getLOGGER().info("Checking oldFile1...");
        String file1 = properties.getProperty("file1");
        if (oldFile1.canRead() && file1.equals(oldFile1.getAbsolutePath())) {
            OLD_FILES = Arrays.toString(new File(CONFIGS_PATH).list());
            Main.getLOGGER().info(file1 + " - " + true);
            Main.getLOGGER().info("Rename oldFile1...");
        } else {
            Main.getLOGGER().info(file1 + " - " + false);
        }
    }
}