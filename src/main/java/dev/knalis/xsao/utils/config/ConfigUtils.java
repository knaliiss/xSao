package dev.knalis.xsao.utils.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils implements IConfig{
    private static ConfigUtils instance;
    private final Properties properties = new Properties();
    private final String configFilePath = "src/main/resources/xSao.properties";

    public ConfigUtils() {
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigUtils getInstance() {
        if (instance == null) instance = new ConfigUtils();
        return instance;
    }



    @Override
    public void save() {
        try (FileOutputStream output = new FileOutputStream(configFilePath)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public String get(String key) {
        return properties.getProperty(key);
    }
}
