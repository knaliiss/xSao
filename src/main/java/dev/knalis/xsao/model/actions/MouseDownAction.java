package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;

import java.awt.*;

public class MouseDownAction extends Action implements IAction {

    public MouseDownAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        if (value instanceof Double) {
            inputManager.mouseDown(winMan.getWindow(), ((Double) value).intValue());
        } else if (value instanceof Integer) {
            inputManager.mouseDown(winMan.getWindow(), (Integer) value);
        }
    }

    @Override
    public String toString() {
        return "MouseDownAction{" +
                "mouse=" + value +
                '}';
    }
}
