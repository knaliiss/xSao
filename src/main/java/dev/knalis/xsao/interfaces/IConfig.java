package dev.knalis.xsao.interfaces;

public interface IConfig {
    void save();
    void set(String key, String value);
    String get(String key);
}
