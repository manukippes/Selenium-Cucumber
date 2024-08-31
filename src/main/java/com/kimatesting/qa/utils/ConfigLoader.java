package com.kimatesting.qa.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();
    private static final String PATH_FILE = "application-%s.properties";

    public static void loadFile(String env) {
        ClassLoader classLoader = ConfigLoader.class.getClassLoader();
        String file = String.format(PATH_FILE, env);
        try (InputStream input = classLoader.getResourceAsStream(file)) {
            properties.load(input);
        } catch (Exception e) {
            throw new UnsupportedOperationException(String.format("Error reading application property file %s - " + e, String.format(PATH_FILE, env)));
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

