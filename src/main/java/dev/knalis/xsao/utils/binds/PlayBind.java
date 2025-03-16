package dev.knalis.xsao.utils.binds;

import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.impl.PlayerUtil;
import dev.knalis.xsao.utils.sound.SoundManager;
import lombok.Data;
import lombok.Getter;

@Data
public class PlayBind implements Bind {
    @Getter
    static boolean playing = false;
    private Integer key;
    @Getter
    private static PlayBind instance;

    public PlayBind(Integer key) {
        this.key = key;
        instance = this;
    }

    @Override
    public void use() {
        MainController.getInstance().getPlayButton().getStyleClass().removeAll("cristalix-close-button", "cristalix-play-button");
        MainController.getInstance().getPlayButton().getStyleClass().add(!playing ? "cristalix-close-button" : "cristalix-play-button");
        String sound = playing ? "play_disable" : "play_enable";
        if (ActionStorageManager.getInstance().getStorage(MainController.getInstance().getSelectedScript()).getList().isEmpty())
            return;
        if (playing) {
            playing = false;
            PlayerUtil.getInstance().stopPlaying();
        } else {
            playing = true;
            PlayerUtil.getInstance().startPlaying();
        }
        SoundManager.getInstance().playSound(sound);
    }

    @Override
    public Integer getKey() {
        return key;
    }

    public String toString() {
        return "PlayBind";
    }
}