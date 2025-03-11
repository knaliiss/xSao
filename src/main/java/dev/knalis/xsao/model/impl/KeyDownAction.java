package dev.knalis.xsao.model.impl;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.model.IAction;

public class KeyDownAction extends Action implements IAction {

    public KeyDownAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        inputManager.keyDown(winMan.getWindow() ,(Integer) value);
    }

}
