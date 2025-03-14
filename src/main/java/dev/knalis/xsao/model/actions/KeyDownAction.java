package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;

public class KeyDownAction extends Action implements IAction {

    public KeyDownAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        if (value instanceof Double) {
            inputManager.keyDown(winMan.getWindow(), ((Double) value).intValue());
        }
    }

    @Override
    public String toString() {
        return "KeyDownAction{" +
                "key=" + value +
                '}';
    }
}
