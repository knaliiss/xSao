package dev.knalis.xsao.model;

import lombok.Getter;

@Getter
public class Action {
    final Long initialTime;
    final Object action;

    public Action(Object action) {
        this.initialTime = System.currentTimeMillis();
        this.action = action;
    }
}
