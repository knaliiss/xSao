package dev.knalis.xsao.utils;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowsManager {
    private static WindowsManager instance;
    WinDef.HWND window;

    private WindowsManager() {
        window = User32.INSTANCE.GetForegroundWindow();
    }

    public static WindowsManager getInstance() {
        if (instance == null) instance = new WindowsManager();
        return instance;
    }
}
