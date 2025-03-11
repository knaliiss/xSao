package dev.knalis.xsao.model;

import dev.knalis.xsao.utils.InputManager;
import dev.knalis.xsao.utils.WindowsManager;
import lombok.Getter;

@Getter
public class Action {
    protected final Long initialTime;
    protected final Object value;
    protected WindowsManager winMan = WindowsManager.getInstance();
    protected InputManager inputManager = InputManager.getInstance();

    public Action(Object value) {
        this.initialTime = System.currentTimeMillis();
        this.value = value;
    }
}
