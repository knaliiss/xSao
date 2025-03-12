package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.model.Bind;

import java.util.HashMap;

public class BindsStorage {
    private static BindsStorage instance;
    private final HashMap<Integer, Bind> binds = new HashMap<>();

    public static BindsStorage getInstance() {
        if (instance == null) {
            instance = new BindsStorage();
        }
        return instance;
    }


    public void add(Integer key, Bind bind) {
        binds.put(key, bind);
    }

    public void remove(Integer key) {
        binds.remove(key);
    }

    public void clear() {
        binds.clear();
    }

    public Bind getBind(Integer key) {
        return binds.get(key);
    }

    public void replaceKey(Integer oldKey, Integer newKey) {
        Bind bind = binds.remove(oldKey);
        if (bind != null) {
            binds.put(newKey, bind);
        }
    }

}