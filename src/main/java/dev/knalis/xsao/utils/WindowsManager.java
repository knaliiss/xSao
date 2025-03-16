package dev.knalis.xsao.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import dev.knalis.xsao.controllers.MainController;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowsManager {
    private boolean winPicks = false;
    private static WindowsManager instance;
    WinDef.HWND window;

    private WindowsManager() {
        window = User32.INSTANCE.GetForegroundWindow();
    }

    public static WindowsManager getInstance() {
        if (instance == null) instance = new WindowsManager();
        return instance;
    }

    public void handleWinPick() {
        if (winPicks) {
            window = User32.INSTANCE.GetForegroundWindow();
            winPicks = false;
            MainController.getInstance().getWinButton().setDisable(false);
        }
    }

    public String getTitle() {
        char[] windowText = new char[512];
        User32.INSTANCE.GetWindowText(window, windowText, 512);
        return Native.toString(windowText);
    }
}
