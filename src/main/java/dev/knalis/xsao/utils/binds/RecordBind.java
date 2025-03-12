package dev.knalis.xsao.utils.binds;

import dev.knalis.xsao.model.Bind;
import dev.knalis.xsao.utils.impl.RecorderUtil;

public class RecordBind implements Bind {
    static boolean recording = false;
    private final Integer key;

    public RecordBind(Integer key) {
        this.key = key;
    }

    @Override
    public void use() {
        if (recording) RecorderUtil.getInstance().stopRecording();
        else RecorderUtil.getInstance().startRecording();
        recording = !recording;
    }

    @Override
    public Integer getKey() {
        return key;
    }
}