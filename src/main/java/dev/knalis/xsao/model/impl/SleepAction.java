package dev.knalis.xsao.model.impl;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.model.IAction;
import lombok.SneakyThrows;

public class SleepAction extends Action implements IAction {
    public SleepAction(Object value) {
        super(value);
    }

    @Override
    @SneakyThrows
    public void action() {
        Thread.sleep((Long) value);
    }
}
