package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;

public class MouseUpAction extends Action implements IAction {

    public MouseUpAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        inputManager.mouseUp(winMan.getWindow(), (Integer) value);
    }

}
