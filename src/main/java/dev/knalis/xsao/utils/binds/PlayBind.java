package dev.knalis.xsao.utils.binds;

import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.impl.PlayerUtil;
import lombok.Data;
import lombok.Getter;

@Data
public class PlayBind implements Bind {
    static boolean playing = false;
    private final Integer key;
    @Getter
    private static PlayBind instance;

    public PlayBind(Integer key) {
        this.key = key;
        instance = this;
    }

    @Override
    public void use() {
        if (playing) PlayerUtil.getInstance().stopPlaying();
        else PlayerUtil.getInstance().startPlaying();

        playing = !playing;
    }

    @Override
    public Integer getKey() {
        return key;
    }
}