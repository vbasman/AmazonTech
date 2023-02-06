package com.amazon.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties = new Properties();

    public ConfigurationReader() {
    }

    public static String getProperty(String keyword) {
        return properties.getProperty(keyword);
    }

    static {
        try {
            FileInputStream file = new FileInputStream("configuration.properties");
            properties.load(file);
            file.close();
        } catch (IOException var1) {
            System.out.println("File not found in the ConfigurationReader class.");
            var1.printStackTrace();
        }

    }
}
