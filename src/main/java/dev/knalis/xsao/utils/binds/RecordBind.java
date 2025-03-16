package dev.knalis.xsao.utils.binds;

import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.impl.RecorderUtil;
import dev.knalis.xsao.utils.sound.SoundManager;
import lombok.Data;
import lombok.Getter;

@Data
public class RecordBind implements Bind {
    boolean recording = false;
    private Integer key;
    @Getter
    private static RecordBind instance;
    private MainController controller;
    private ActionStorageManager storage;

    public RecordBind(Integer key) {
        this.key = key;
        instance = this;
    }

    @Override
    public void use() {
        controller = MainController.getInstance();
        storage = ActionStorageManager.getInstance();
        controller.getRecordButton().getStyleClass().removeAll("cristalix-close-button", "cristalix-play-button");
        controller.getRecordButton().getStyleClass().add(!recording ? "cristalix-close-button" : "cristalix-play-button");
        String sound = recording ? "rec_disable" : "rec_enable";
        if (recording) {
            RecorderUtil.getInstance().stopRecording();
        } else {
            RecorderUtil.getInstance().startRecording();
        }

        SoundManager.getInstance().playSound(sound);

        recording = !recording;
        if (!recording)
            controller.getScriptTextArea().setText(storage.getStorage(controller.getSelectedScript()).getList().toString());
    }

    @Override
    public Integer getKey() {
        return key;
    }

    public String toString() {
        return "RecordBind";
    }

}