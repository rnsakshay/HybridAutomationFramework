package com.akshay.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties props;

    static {
        String targetEnv = props.getProperty("env");
        if (targetEnv == null || targetEnv.isEmpty()) {
            targetEnv = "qa";
        }

        targetEnv = targetEnv.toLowerCase().trim();
        String configFilePath = "src/main/resources/config." + targetEnv + ".properties";

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Framework Critical Failure: " +
                    "Could not load configuration file mapping for environment target: " + targetEnv, e);
        }
    }
    /**
     * Extracts configuration values from the loaded environment property file.
     * @param key The target configuration parameter name.
     * @return Trimmed string value matching the property key.
     */
    public static  String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null){
            throw new RuntimeException("Configuration Mapping Exception: The key '" + key + "' was not found inside the active environment configuration file.");
        }
        return value;
    }
}
