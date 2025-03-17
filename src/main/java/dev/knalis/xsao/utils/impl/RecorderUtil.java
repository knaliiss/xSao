package dev.knalis.xsao.utils.impl;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.sun.jna.platform.win32.WinUser;
import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.interfaces.IRecorderUtil;
import dev.knalis.xsao.model.actions.*;
import dev.knalis.xsao.utils.ActionStorageManager;

import java.io.IOException;

import static dev.knalis.xsao.utils.InputManager.ExtendedUser32;

public class RecorderUtil implements IRecorderUtil, NativeKeyListener, NativeMouseListener {
    private static RecorderUtil instance;
    volatile boolean isRecording = false;
    ActionStorage storage;
    long lastAdd;

    @Override
    public void startRecording() {
        storage = ActionStorageManager.getInstance().getStorage(MainController.getInstance().getSelectedScript());
        storage.clear();
        lastAdd = 0;
        isRecording = true;
        try {
            if (!GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.registerNativeHook();
            }
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);

        storage.add(new SleepAction(getCoolDown()));
    }

    @Override
    public void stopRecording() {
        isRecording = false;
        GlobalScreen.removeNativeKeyListener(this);
        GlobalScreen.removeNativeMouseListener(this);

        try {
            ActionStorageManager.getInstance().saveAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        int scanCode = e.getKeyCode();
        int virtualKeyCode = ExtendedUser32.INSTANCE.MapVirtualKey(scanCode, WinUser.MAPVK_VSC_TO_VK);

        if (BindsStorage.getInstance().isBindExist(virtualKeyCode)) return;

        storage.add(new KeyDownAction(virtualKeyCode));
        storage.add(new SleepAction(getCoolDown()));
        sideLogicHelper();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        int scanCode = e.getKeyCode();
        int virtualKeyCode = ExtendedUser32.INSTANCE.MapVirtualKey(scanCode, WinUser.MAPVK_VSC_TO_VK);

        if (BindsStorage.getInstance().isBindExist(virtualKeyCode)) return;

        storage.add(new KeyUpAction(virtualKeyCode));
        storage.add(new SleepAction(getCoolDown()));
        sideLogicHelper();
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        storage.add(new MouseDownAction(e.getButton()));
        storage.add(new SleepAction(getCoolDown()));
        sideLogicHelper();
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        storage.add(new MouseUpAction(e.getButton()));
        storage.add(new SleepAction(getCoolDown()));
        sideLogicHelper();
    }

    private void sideLogicHelper() {
//        List<IAction> actions = storage.getList();
//        if (actions != null && !actions.isEmpty()) MainController.getInstance().getScriptTextArea().setText(actions.toString());
    }

    private long getCoolDown() {
        if (lastAdd == 0) {
            lastAdd = System.currentTimeMillis();
            return 0;
        }
        long currentTime = System.currentTimeMillis();
        long coolDown = currentTime - lastAdd;
        lastAdd = currentTime;
        return coolDown;
    }

    public static RecorderUtil getInstance() {
        if (instance == null) {
            instance = new RecorderUtil();
        }
        return instance;
    }
}
