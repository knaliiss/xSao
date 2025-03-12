package dev.knalis.xsao.utils;

import dev.knalis.xsao.model.Script;
import dev.knalis.xsao.utils.impl.ActionStorage;
import lombok.Getter;

import java.util.HashMap;

public class Util{
    @Getter
    private static HashMap<String, Script> scripts = new HashMap<>();

    private static Script createScript(String name, ActionStorage storage) {
        if (scripts.containsKey(name)) throw new IllegalArgumentException("Duplicate script name: " + name);
        Script script = new Script(name, storage);
        scripts.put(script.getName(), script);
        return script;
    }

    public static Script getScript(String name) {
        return scripts.get(name);
    }

    public static void removeScript(String name) {
        scripts.remove(name);
    }


}
