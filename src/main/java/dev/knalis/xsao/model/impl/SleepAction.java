package dev.knalis.xsao.model.impl;

import dev.knalis.xsao.model.Action;
import dev.knalis.xsao.model.IAction;

public class SleepAction extends Action implements IAction {
    public SleepAction(Object value) {
        super(value);
    }

    @Override
    public void action() {
        try {
            Thread.sleep((Long) value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getTime() {
        return 0;
    }
}
