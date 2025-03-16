package dev.knalis.xsao.controllers;

import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.interfaces.IBindSwapper;
import dev.knalis.xsao.utils.binds.BindUtil;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BindSwapper implements IBindSwapper {
    @Override
    public void swap(Bind bind) {
        BindUtil.getInstance().setBindSwitch(true);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);


        StackPane root = new StackPane();
        root.setPrefSize(400, 200);
        root.setAlignment(Pos.CENTER);

        Rectangle background = new Rectangle(400, 200);
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(Color.web("#2a2a2a"));
        background.setStroke(Color.web("#555"));
        background.setStrokeWidth(2);
        background.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));

        Label label = new Label("Press any key to swap...");
        label.getStyleClass().add("dialog-label");
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        root.getChildren().addAll(background, label);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(250), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        stage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.ESCAPE) {
                BindUtil.getInstance().switchBind(bind, event.getCode().getCode());
            }

            FadeTransition fadeOut = new FadeTransition(Duration.millis(250), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> stage.close());
            fadeOut.play();
        });
    }
}
