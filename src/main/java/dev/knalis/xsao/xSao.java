package dev.knalis.xsao;

import dev.knalis.xsao.utils.config.ConfigUtils;
import dev.knalis.xsao.utils.windows.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class xSao extends Application {

    @Override
    public void start(Stage stage) {
        String fxml = ConfigUtils.getInstance().get("fxml.main");
        SceneLoader sceneLoader = new SceneLoader(fxml, stage);
        sceneLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}