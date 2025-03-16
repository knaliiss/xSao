package dev.knalis.xsao.utils.binds;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.sun.jna.platform.win32.WinUser;
import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.InputManager;
import dev.knalis.xsao.utils.WindowsManager;
import dev.knalis.xsao.utils.config.ConfigUtils;
import dev.knalis.xsao.utils.impl.BindsStorage;
import javafx.scene.control.Button;
import lombok.Setter;

import java.awt.event.KeyEvent;


@Setter
public class BindUtil implements NativeKeyListener {
    private static BindUtil instance;
    private boolean bindSwitch = false;

    public static BindUtil getInstance() {
        if (instance == null) instance = new BindUtil();
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
        int vk = convVscToVk(e.getKeyCode());
        if (bindSwitch) return;

        if (WindowsManager.getInstance().isWinPicks() && vk == convVscToVk(28)) {
            WindowsManager.getInstance().handleWinPick();
        }

        Bind bind = BindsStorage.getInstance().getBind(vk);
        if (bind != null) bind.use();

    }

    public void switchBind(Bind bind, Integer newKey) {
        BindsStorage.getInstance().replaceKey(bind.getKey(), newKey);
        bind.setKey(newKey);
        String keyName = bind instanceof PlayBind ? "key.play" : "key.record";
        ConfigUtils.getInstance().set(keyName, newKey.toString());
        ConfigUtils.getInstance().save();

        Button button = bind instanceof PlayBind ? MainController.getInstance().getPlayButton() : MainController.getInstance().getRecordButton();
        String buttonText = button.getText().split(" ")[0];
        button.setText(buttonText + " [" + KeyEvent.getKeyText(newKey) + "]");
        bindSwitch = false;
    }

    private int convVscToVk(int nativeKeyCode) {
        return InputManager.ExtendedUser32.INSTANCE.MapVirtualKey(nativeKeyCode, WinUser.MAPVK_VSC_TO_VK);
    }
}
