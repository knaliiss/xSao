package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;

public class MouseUpAction extends Action implements IAction {

    public MouseUpAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        if (value instanceof Double) {
            inputManager.mouseUp(winMan.getWindow(), ((Double) value).intValue());
        }
    }

    @Override
    public String toString() {
        return "MouseUpAction{" +
                "mouse=" + value +
                '}';
    }
}
