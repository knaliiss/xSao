package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;

public class MouseDownAction extends Action implements IAction {

    public MouseDownAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        inputManager.mouseDown(winMan.getWindow() ,(Integer) value);
    }

}
