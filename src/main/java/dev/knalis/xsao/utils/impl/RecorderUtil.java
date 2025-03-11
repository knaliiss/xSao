package dev.knalis.xsao.utils.impl;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import dev.knalis.xsao.model.IAction;
import dev.knalis.xsao.model.impl.KeyDownAction;
import dev.knalis.xsao.model.impl.KeyUpAction;
import dev.knalis.xsao.model.impl.MouseDownAction;
import dev.knalis.xsao.model.impl.MouseUpAction;
import dev.knalis.xsao.utils.IRecorderUtil;
import dev.knalis.xsao.utils.IStorage;

public class RecorderUtil implements IRecorderUtil, NativeKeyListener, NativeMouseListener {
    volatile boolean isRecording = false;
    IStorage<IAction> storage = ActionStorage.getInstance();
    long lastAdd;

    @Override
    public void startRecording() {
        storage.clear();
        lastAdd = Long.parseLong(null);
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
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        storage.add(new KeyUpAction(e.getKeyCode()));
    }
    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        storage.add(new MouseDownAction(e.getButton()));
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        storage.add(new MouseUpAction(e.getButton()));
    }

    private long getCoolDown(){
        if (lastAdd != null) {
            lastAdd = System.currentTimeMillis();
            return System.currentTimeMillis() - lastAdd();
        }
        lastAdd = System.currentTimeMillis();
        return 0;
    }
}
