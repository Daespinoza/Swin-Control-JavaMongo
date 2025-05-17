package org.daespinoza;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String PROPERTIES_FILE = "config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = AppConfig.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {

            if (inputStream == null) {
                throw new RuntimeException("Unable to find " + PROPERTIES_FILE);
            }

            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
