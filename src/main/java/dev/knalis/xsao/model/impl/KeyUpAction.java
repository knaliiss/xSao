package dev.knalis.xsao.model.impl;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.model.IAction;

public class KeyUpAction extends Action implements IAction {

    public KeyUpAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        inputManager.keyUp(winMan.getWindow() ,(Integer) value);
    }

    @Override
    public long getTime() {
        return initialTime;
    }
}
