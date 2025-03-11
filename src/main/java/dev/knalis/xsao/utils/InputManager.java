package dev.knalis.xsao.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import lombok.SneakyThrows;

public class InputManager {
    private static InputManager instance;

    public static InputManager getInstance() {
        if (instance == null) instance = new InputManager();
        return instance;
    }

    public interface ExtendedUser32 extends Library {
        ExtendedUser32 INSTANCE = Native.load("user32", ExtendedUser32.class);
        int MapVirtualKey(int uCode, int uMapType);
    }

    @SneakyThrows
    public void keyDown(HWND targetWindow, int keyCode) {
        int scanCode = ExtendedUser32.INSTANCE.MapVirtualKey(keyCode, 0);
        long lParamDown = 0x00000001L | ((long) scanCode << 16);
        User32.INSTANCE.PostMessage(targetWindow, WinUser.WM_KEYDOWN, new WPARAM(keyCode), new LPARAM(lParamDown));
        Thread.sleep(20);
    }

    @SneakyThrows
    public void keyUp(HWND targetWindow, int keyCode) {
        int scanCode = ExtendedUser32.INSTANCE.MapVirtualKey(keyCode, 0);
        long lParamUp = 0xC0000001L | ((long) scanCode << 16);
        User32.INSTANCE.PostMessage(targetWindow, WinUser.WM_KEYUP, new WPARAM(keyCode), new LPARAM(lParamUp));
        Thread.sleep(50);
    }

    @SneakyThrows
    public void mouseDown(HWND targetWindow, int mouseCode) {
//        User32.INSTANCE.PostMessage(targetWindow, WinUser.WM_LBUTTONDOWN, new WPARAM(mouseCode), new LPARAM(0));
//        Thread.sleep(20);
        //TODO
    }

    @SneakyThrows
    public void mouseUp(HWND targetWindow, int mouseCode) {
//        User32.INSTANCE.PostMessage(targetWindow, WinUser.WM_LBUTTONUP, new WPARAM(mouseCode), new LPARAM(0));
//        Thread.sleep(50);
        //TODO
    }
}
