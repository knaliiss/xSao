package dev.knalis.xsao.model.actions;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.interfaces.IAction;
import lombok.SneakyThrows;

public class SleepAction extends Action implements IAction {
    public SleepAction(Object value) {
        super(value);
    }

    @Override
    @SneakyThrows
    public void action() {
        if (value instanceof Double) {
            Thread.sleep(((Double) value).longValue());
        }
    }

    @Override
    public String toString() {
        return "SleepAction{" +
                "sleep=" + value +
                " ms}";
    }
}
