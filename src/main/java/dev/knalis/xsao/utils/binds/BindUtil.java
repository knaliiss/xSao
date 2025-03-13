package dev.knalis.xsao.utils.binds;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.impl.BindsStorage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindUtil implements NativeKeyListener {
    private static BindUtil instance;
    private boolean bindSwitch = false;

    public static BindUtil getInstance() {
        if (instance == null) instance = new BindUtil();
        return instance;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (bindSwitch) return;
        BindsStorage.getInstance().getBind(e.getKeyCode()).use();
    }

    public void switchBind(Bind bind, Integer newKey) {
        BindsStorage.getInstance().replaceKey(bind.getKey(), newKey);
    }

}