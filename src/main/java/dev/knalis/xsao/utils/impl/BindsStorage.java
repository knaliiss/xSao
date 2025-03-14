package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.interfaces.Bind;

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

    public void add(Bind bind) {
        binds.put(bind.getKey(), bind);
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

    public boolean isBindExist(Integer key) {
        return binds.containsKey(key);
    }

}