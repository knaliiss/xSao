package dev.knalis.xsao.utils.impl;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import dev.knalis.xsao.model.RecordedPoint;
import dev.knalis.xsao.utils.IRecorderUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RecorderUtil implements IRecorderUtil, NativeKeyListener, NativeMouseListener {
    private final LinkedList<RecordedPoint> actions = new LinkedList<>();
    volatile boolean isRecording = false;

    @Override
    public void startRecording() {
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
        actions.add(new RecordedPoint(e));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        actions.add(new RecordedPoint(e));
    }
}
