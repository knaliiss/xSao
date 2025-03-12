package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.model.Bind;
import dev.knalis.xsao.utils.IStorage;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.List;

public class BindsStorage {
    private static BindsStorage instance;
    private final HashMap<List<KeyCode>, Bind> binds = new HashMap<>();

    public static BindsStorage getInstance() {
        if (instance == null) {
            instance = new BindsStorage();
        }
        return instance;
    }


    public void add(List<KeyCode> keys, Bind bind) {
        binds.put(keys, bind);
    }

    public void remove(List<KeyCode> keys) {
        binds.remove(keys);
    }

    public void clear() {
        binds.clear();
    }

    public Bind getBind(List<KeyCode> keys) {
        return binds.get(keys);
    }
}