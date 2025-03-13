package dev.knalis.xsao.utils.impl;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.model.actions.*;
import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.interfaces.IRecorderUtil;

public class RecorderUtil implements IRecorderUtil, NativeKeyListener, NativeMouseListener {
    private static RecorderUtil instance;
    volatile boolean isRecording = false;
    ActionStorage storage = ActionStorageManager.getInstance().getStorage(MainController.getInstance().getSelectedScript());
    long lastAdd;

    @Override
    public void startRecording() {
        storage.clear();
        lastAdd = 0;
        isRecording = true;
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
    }

    @Override
    public void stopRecording() {
        isRecording = false;
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        storage.add(new KeyDownAction(e.getKeyCode()));
        storage.add(new SleepAction(getCoolDown()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        storage.add(new KeyUpAction(e.getKeyCode()));
        storage.add(new SleepAction(getCoolDown()));
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        storage.add(new MouseDownAction(e.getButton()));
        storage.add(new SleepAction(getCoolDown()));
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        storage.add(new MouseUpAction(e.getButton()));
        storage.add(new SleepAction(getCoolDown()));
    }

    private long getCoolDown() {
        if (lastAdd == 0) {
            lastAdd = System.currentTimeMillis();
            return 0;
        }

        long currentTime = System.currentTimeMillis();
        long coolDown = currentTime - lastAdd;
        lastAdd = currentTime;
        System.out.println(coolDown + " ms");
        return coolDown;
    }

    public static RecorderUtil getInstance() {
        if (instance == null) {
            instance = new RecorderUtil();
        }
        return instance;
    }
}
