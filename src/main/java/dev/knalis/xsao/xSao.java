package dev.knalis.xsao;

import dev.knalis.xsao.utils.IRecorderUtil;
import dev.knalis.xsao.utils.impl.RecorderUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class xSao extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(xSao.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        IRecorderUtil recorderUtil = new RecorderUtil();
        recorderUtil.startRecording();
    }

    public static void main(String[] args) {
        launch();
    }
}