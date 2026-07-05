package com.akshay.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

        private static final Properties properties = new Properties();
        private static final String ENV = System.getProperty("env", "qa").trim().toLowerCase();
        private static final String FILE_NAME =  "config." + ENV + ".properties";

        static {

            try (InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream(FILE_NAME)) {

                if (is == null) {
                    throw new IllegalStateException(
                            "Configuration file not found for environment: '"+ENV+"': " + FILE_NAME);
                }

                properties.load(is);

            } catch (IOException e) {
                throw new IllegalStateException(
                        "Unable to load configuration: " + FILE_NAME, e);
            }
        }
        private ConfigReader() {

          }
        public static String getProperty(String key) {

            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Property key cannot be null or blank.");
            }

            String value = properties.getProperty(key);

            if (value == null || value.isBlank()) {
                throw new IllegalStateException(
                        "Configuration key '" + key + "' is missing in " + FILE_NAME
                );
            }

            return value.trim();
        }
}
