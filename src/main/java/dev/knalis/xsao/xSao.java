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

import java.io.IOException;

public class xSao extends Application {


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
            throw new RuntimeException("Error while initializing binds", e);
        }
        try {
            ActionStorageManager.getInstance().loadAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BindUtil.getInstance().register();
        launch();
    }
}