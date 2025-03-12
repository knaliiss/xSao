package dev.knalis.xsao.utils.config;

public interface IConfig {
    void save();
    void set(String key, String value);
    String get(String key);
}
