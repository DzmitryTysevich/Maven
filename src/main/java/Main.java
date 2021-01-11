import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        File oldFile1 = new File("src/main/resources/old_file1.json");
        File oldFile2 = new File("src/main/resources/old_file2.json");

        String file1 = properties.getProperty("file1");
        if (oldFile1.canRead() && file1.equals(oldFile1.getAbsolutePath())) {
            LOGGER.info(file1 + " - " + true);
        } else {
            LOGGER.info(file1 + " - " + false);
        }

        String file2 = properties.getProperty("file2");
        if (oldFile2.canRead() && file2.equals(oldFile2.getAbsolutePath())) {
            LOGGER.info(file2 + " - " + true);
        } else {
            LOGGER.info(file2 + " - " + false);
        }

        String suffix = properties.getProperty("suffix");
        String path = properties.getProperty("path");

        oldFile1.renameTo(new File(path + suffix + "_file1.json"));
        oldFile2.renameTo(new File(path + suffix + "_file2.json"));

        File resources = new File("/home/dzmitry/IdeaProjects/Maven/src/main/resources");
        LOGGER.info(Arrays.toString(resources.list()));
    }
}