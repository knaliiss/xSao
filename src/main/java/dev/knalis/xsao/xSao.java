package dev.knalis.xsao;

import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.binds.BindUtil;
import dev.knalis.xsao.utils.binds.PlayBind;
import dev.knalis.xsao.utils.binds.RecordBind;
import dev.knalis.xsao.utils.config.ConfigUtils;
import dev.knalis.xsao.utils.impl.BindsStorage;
import dev.knalis.xsao.utils.windows.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class xSao extends Application {
    private static final Logger logger = Logger.getLogger(xSao.class.getName());
    
    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        String fxml = ConfigUtils.getInstance().get("fxml.main");
        SceneLoader sceneLoader = new SceneLoader(fxml, stage);
        sceneLoader.load();
    }

    public static void main(String[] args) {
        try {
            new PlayBind(Integer.parseInt(ConfigUtils.getInstance().get("key.play")));
            new RecordBind(Integer.parseInt(ConfigUtils.getInstance().get("key.record")));
            BindsStorage.getInstance().add(PlayBind.getInstance());
            BindsStorage.getInstance().add(RecordBind.getInstance());
        } catch (Exception e) {
            logger.severe("Error while initializing binds");
            throw new RuntimeException("Error while initializing binds", e);
        }
        try {
            ActionStorageManager.getInstance().loadAll();
        } catch (IOException e) {
            logger.severe("Error while loading actions");
            throw new RuntimeException(e);
        }
        BindUtil.getInstance().register();
        launch();
    }
}
