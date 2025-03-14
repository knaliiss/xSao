package dev.knalis.xsao.utils.binds;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.WindowsManager;
import dev.knalis.xsao.utils.impl.BindsStorage;

public class BindUtil implements NativeKeyListener {
    private static BindUtil instance;
    private boolean bindSwitch = false;

    public static BindUtil getInstance() {
        if (instance == null) {
            instance = new BindUtil();
        }
        return instance;
    }

    public void register() {
        try {
            if (!GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.registerNativeHook();
            }
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (bindSwitch) return;
        if (WindowsManager.getInstance().isWinPicks() && e.getKeyCode() == 28)
            WindowsManager.getInstance().handleWinPick();
        Bind bind = BindsStorage.getInstance().getBind(e.getKeyCode());
        if (bind != null) {
            bind.use();
        }
    }

    public void switchBind(Bind bind, Integer newKey) {
        BindsStorage.getInstance().replaceKey(bind.getKey(), newKey);
    }
}
