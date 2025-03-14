package dev.knalis.xsao.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.knalis.xsao.utils.config.GsonConfig;
import dev.knalis.xsao.utils.impl.ActionStorage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActionStorageManager {
    private static ActionStorageManager instance;
    private static final String CONFIG_FILE = System.getProperty("user.home") + "/xSao/config.json";

    private static final Gson gson = GsonConfig.createGson();
    private final Map<String, ActionStorage> storages = new HashMap<>();

    public ActionStorage getStorage(String name) {
        return storages.get(name);
    }

    public void removeStorage(String name) {
        storages.remove(name);
    }

    public void saveAll() throws IOException {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(storages, writer);
        }
    }

    public void loadAll() throws IOException {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (reader.readLine() == null) {
                storages.put("default", new ActionStorage(new LinkedList<>()));
                return;
            }
        }

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, ActionStorage>>() {}.getType();
            Map<String, ActionStorage> loadedStorages = gson.fromJson(reader, type);
            storages.clear();
            if (loadedStorages != null) {
                storages.putAll(loadedStorages);
            } else {
                storages.put("default", new ActionStorage(new LinkedList<>()));
            }
        }
    }

    public void createStorage(String name) {
        storages.put(name, new ActionStorage(new LinkedList<>()));
    }

    public List<String> getStorageNames() {
        return new LinkedList<>(storages.keySet());
    }

    public static ActionStorageManager getInstance() {
        if (instance == null) {
            instance = new ActionStorageManager();
        }
        return instance;
    }
}
