package dev.knalis.xsao.utils.binds;

import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.impl.RecorderUtil;
import lombok.Data;
import lombok.Getter;

@Data
public class RecordBind implements Bind {
    static boolean recording = false;
    private final Integer key;
    @Getter
    private static RecordBind instance;

    public RecordBind(Integer key) {
        this.key = key;
        instance = this;
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