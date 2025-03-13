package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.interfaces.IAction;
import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.interfaces.IPlayerUtil;

public class PlayerUtil implements IPlayerUtil {
    ActionStorage storage = ActionStorageManager.getInstance().getStorage(MainController.getInstance().getSelectedScript());
    static PlayerUtil instance;
    Thread playingThread;

    @Override
    public void startPlaying() {
        playingThread = new Thread(() -> {
            for (int i = 0; i < storage.getList().size(); i++) {
                IAction currentAction = storage.getList().get(i);
                currentAction.action();
            }
        });
        playingThread.start();
    }

    @Override
    public void stopPlaying() {
        if (playingThread != null && playingThread.isAlive()) {
            playingThread.interrupt();
        }
    }

    public static PlayerUtil getInstance(){
        if(instance == null) {
            instance = new PlayerUtil();
        }
        return instance;
    }

}
