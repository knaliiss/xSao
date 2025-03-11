package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.model.Bind;
import dev.knalis.xsao.utils.IStorage;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.List;

public class BindsStorage implements IStorage {
    private static BindsStorage instance;
    private HashMap<Bind, List<KeyCode>> binds;

    @Override
    public void add(Object obj) {

    }

    @Override
    public void remove(Object obj) {
    }

    @Override
    public void clear() {
    }
    @Override
    public void add(KeyCode key, Bind value){
        binds.put(key, value);
    }
}
