package dev.knalis.xsao.model;

import lombok.Getter;

@Getter
public class RecordedPoint {
    final Long timestamp;
    final ActionType actionType;
    final Object action;

    public RecordedPoint(Object action, ActionType actionType) {
        timestamp = System.currentTimeMillis();
        this.action = action;
        this.actionType = actionType;
    }
}
