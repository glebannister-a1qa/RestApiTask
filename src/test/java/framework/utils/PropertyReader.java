package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties;
    private static final String PATH_TO_CONFIG = "src/main/resources/config.properties";
    private static final String PATH_TO_TEST_DATA = "src/main/resources/testData.properties";
    private static final String PATH_TO_DB_DATA = "src/main/resources/db.properties";

    private static void initPropertyReader(String path) {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            LogUtil.error(e.toString());
        }
    }

    public static String getConfigValue(String key) {
        initPropertyReader(PATH_TO_CONFIG);
        return properties.getProperty(key);
    }

    public static String getDataValue(String key) {
        initPropertyReader(PATH_TO_TEST_DATA);
        return properties.getProperty(key);
    }

    public static String getDbData(String key) {
        initPropertyReader(PATH_TO_DB_DATA);
        return properties.getProperty(key);
    }
}
