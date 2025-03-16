package dev.knalis.xsao.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.W32APIOptions;
import lombok.SneakyThrows;

public class InputManager {
    private static InputManager instance;
    public static final int WM_LBUTTONDOWN = 0x0201;
    public static final int WM_RBUTTONDOWN = 0x0204;
    public static final int WM_MBUTTONDOWN = 0x0207;
    public static final int WM_LBUTTONUP = 0x0202;
    public static final int WM_RBUTTONUP = 0x0205;
    public static final int WM_MBUTTONUP = 0x0208;

    public static InputManager getInstance() {
        if (instance == null) instance = new InputManager();
        return instance;
    }

    public interface ExtendedUser32 extends Library {
        ExtendedUser32 INSTANCE = Native.load("user32", ExtendedUser32.class, W32APIOptions.DEFAULT_OPTIONS);

        int MapVirtualKey(int uCode, int uMapType);
    }

    @SneakyThrows
    public void keyDown(HWND targetWindow, int keyCode) {
        int scanCode = ExtendedUser32.INSTANCE.MapVirtualKey(keyCode, 0);
        long lParamDown = 0x00000001L | ((long) scanCode << 16);
        User32.INSTANCE.PostMessage(targetWindow, WinUser.WM_KEYDOWN, new WPARAM(keyCode), new LPARAM(lParamDown));
    }

    @SneakyThrows
    public void keyUp(HWND targetWindow, int keyCode) {
        int scanCode = ExtendedUser32.INSTANCE.MapVirtualKey(keyCode, 0);
        long lParamUp = 0xC0000001L | ((long) scanCode << 16);
        User32.INSTANCE.PostMessage(targetWindow, WinUser.WM_KEYUP, new WPARAM(keyCode), new LPARAM(lParamUp));
    }


    public void mouseDown(HWND targetWindow, int mouseCode) {
        mouseHandle(targetWindow, mouseCode, WM_LBUTTONDOWN, WM_RBUTTONDOWN, WM_MBUTTONDOWN);

    }

    public void mouseUp(HWND targetWindow, int mouseCode) {
        mouseHandle(targetWindow, mouseCode, WM_LBUTTONUP, WM_RBUTTONUP, WM_MBUTTONUP);

    }

    private void mouseHandle(HWND targetWindow, int mouseCode, int wmLbuttonup, int wmRbuttonup, int wmMbuttonup) {
        int message = switch (mouseCode) {
            case 1 -> wmLbuttonup;
            case 2 -> wmRbuttonup;
            case 3 -> wmMbuttonup;
            default -> throw new IllegalArgumentException("Invalid mouse button code: " + mouseCode);
        };
        User32.INSTANCE.PostMessage(targetWindow, message, new WPARAM(0), new LPARAM(0));
    }
}
