package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.interfaces.IAction;
import dev.knalis.xsao.interfaces.IStorage;

import java.util.LinkedList;

public class ActionStorage implements IStorage<IAction> {
    private final LinkedList<IAction> actions;

    public ActionStorage(LinkedList<IAction> actions) {
        this.actions = actions;
    }

    @Override
    public LinkedList<IAction> getList() {
        return actions;
    }

    @Override
    public void add(IAction obj) {
        actions.add(obj);
    }

    @Override
    public void remove(IAction obj) {
        actions.remove(obj);
    }

    @Override
    public void clear() {
        actions.clear();
    }

}