package dev.knalis.xsao.utils.binds;

import dev.knalis.xsao.model.Bind;
import dev.knalis.xsao.utils.impl.PlayerUtil;

public class PlayBind implements Bind {
    static boolean playing = false;
    @Override
    public void use() {
        if (playing) PlayerUtil.getInstance().stopPlaying();
        else PlayerUtil.getInstance().startPlaying();

        playing = !playing;
    }
}
