package dev.knalis.xsao.utils.impl;

import dev.knalis.xsao.model.IAction;
import dev.knalis.xsao.utils.IPlayerUtil;

public class PlayerUtil implements IPlayerUtil {
    ActionStorage storage = ActionStorage.getInstance();
    Thread playingThread;
    @Override
    public void startPlaying() {
        playingThread = new Thread(() -> {
            for (int i = 0; i < storage.getList().size(); i++) {
                IAction currentAction = storage.getList().get(i);
                currentAction.action();
                try {
                    if (i < storage.getList().size() - 1) {
                        IAction nextAction = storage.getList().get(i + 1);
                        long sleepTime = nextAction.getTime() - currentAction.getTime();
                        Thread.sleep(sleepTime);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
}
