package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;

public class KeyUpAction extends Action implements IAction {

    public KeyUpAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        if (value instanceof Double) {
            inputManager.keyUp(winMan.getWindow(), ((Double) value).intValue());
        } else if (value instanceof Integer) {
            inputManager.keyUp(winMan.getWindow(), (Integer) value);
        }
    }

    @Override
    public String toString() {
        return "KeyUpAction{" +
                "key=" + value +
                '}';
    }
}
