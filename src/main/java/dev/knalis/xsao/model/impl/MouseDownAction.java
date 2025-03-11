package dev.knalis.xsao.model.impl;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.model.IAction;

public class MouseDownAction extends Action implements IAction {

    public MouseDownAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        inputManager.mouseDown(winMan.getWindow() ,(Integer) value);
    }

}
