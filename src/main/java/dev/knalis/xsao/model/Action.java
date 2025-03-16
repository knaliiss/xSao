package dev.knalis.xsao.model;

import dev.knalis.xsao.utils.InputManager;
import dev.knalis.xsao.utils.WindowsManager;
import lombok.Getter;

@Getter
public class Action {
    protected Object value;
    protected WindowsManager winMan = WindowsManager.getInstance();
    protected InputManager inputManager = InputManager.getInstance();

    public Action(Object value) {
        this.value = value;
    }
}
