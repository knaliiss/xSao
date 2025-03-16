package dev.knalis.xsao.utils.config;

import dev.knalis.xsao.interfaces.IConfig;

import java.io.*;
import java.util.Properties;

public class ConfigUtils implements IConfig {
    private static ConfigUtils instance;
    private final Properties properties = new Properties();
    private final String configFilePath = "xSao.properties";

    public ConfigUtils() {
        File configFile = new File(configFilePath);

        if (!configFile.exists()) {
            createDefaultConfigFile();
        }

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
            properties.store(output, "Configuration updated");
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

    private void createDefaultConfigFile() {
        properties.setProperty("fxml.main", "main.fxml");
        properties.setProperty("key.play", "85");
        properties.setProperty("key.record", "89");

        try (FileOutputStream output = new FileOutputStream(configFilePath)) {
            properties.store(output, "Default configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}