package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.controllers.MainController;
import dev.knalis.xsao.interfaces.IAction;
import dev.knalis.xsao.interfaces.IPlayerUtil;
import dev.knalis.xsao.model.actions.KeyDownAction;
import dev.knalis.xsao.model.actions.KeyUpAction;
import dev.knalis.xsao.model.actions.MouseDownAction;
import dev.knalis.xsao.model.actions.MouseUpAction;
import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.binds.PlayBind;

public class PlayerUtil implements IPlayerUtil {
    ActionStorage storage;
    static PlayerUtil instance;
    Thread playingThread;
    IAction currentAction;

    public void updateStorage() {
        storage = ActionStorageManager.getInstance().getStorage(MainController.getInstance().getSelectedScript());
    }

    @Override
    public void startPlaying() {
        playingThread = new Thread(() -> {
            while (PlayBind.isPlaying()) {
                updateStorage();
                try {
                    for (int i = 0; i < storage.getList().size(); i++) {
                        currentAction = storage.getList().get(i);
                        currentAction.action();
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        playingThread.start();
    }


    @Override
    public void stopPlaying() {
        System.out.println(PlayBind.isPlaying());
        if (playingThread != null && playingThread.isAlive()) {
            playingThread.interrupt();
            playingThread = null;
        }
    }

    public static PlayerUtil getInstance() {
        if (instance == null) {
            instance = new PlayerUtil();
        }
        return instance;
    }

}
