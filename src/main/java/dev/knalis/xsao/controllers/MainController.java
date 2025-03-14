package dev.knalis.xsao.controllers;

import dev.knalis.xsao.interfaces.Bind;
import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.WindowsManager;
import dev.knalis.xsao.utils.binds.BindUtil;
import dev.knalis.xsao.utils.binds.PlayBind;
import dev.knalis.xsao.utils.binds.RecordBind;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Getter;

@Data
public class MainController {
    @Getter
    private static MainController instance;
    @FXML
    Pane topPanel, backPain;

    @FXML
    ComboBox<String> scriptsComboBox;

    @FXML
    TextArea scriptTextArea;

    @FXML
    private TextField nameTextField;

    @FXML
    Button closeButton, minimizeButton, recordButton, playButton, winButton, createButton, removeButton;

    @FXML
    void initialize() {
        instance = this;
        initButtons();
        scriptsComboBox.getItems().addAll(ActionStorageManager.getInstance().getStorageNames());
        scriptsComboBox.getSelectionModel().select(0);
        scriptTextArea.setEditable(false);
        scriptTextArea.setWrapText(true);
        scriptTextArea.setText(ActionStorageManager.getInstance().getStorage(scriptsComboBox.getSelectionModel().getSelectedItem()).getList().toString());
    }

    public String getSelectedScript() {
        return scriptsComboBox.getSelectionModel().getSelectedItem();
    }

    private void initButtons() {
        closeButton.setOnMouseClicked(event -> System.exit(0));
        minimizeButton.setOnMouseClicked(event -> ((Stage) minimizeButton.getScene().getWindow()).setIconified(true));
        recordButton.setOnMouseClicked(this::handleRecordButton);
        playButton.setOnMouseClicked(this::handlePlayButton);
        winButton.setOnMouseClicked(mouseEvent -> handleWinButton());
        createButton.setOnMouseClicked(event -> handleCreateButton());
        removeButton.setOnMouseClicked(event -> handleRemoveButton());
    }

    private void handleWinButton() {
        winButton.setDisable(true);
        WindowsManager.getInstance().setWinPicks(true);
    }



    private void handlePlayButton(MouseEvent event) {
        if (event.getButton() == MouseButton.MIDDLE) handleSwapBind(PlayBind.getInstance());
        else PlayBind.getInstance().use();
    }

    private void handleRecordButton(MouseEvent event) {
        if (event.getButton() == MouseButton.MIDDLE) handleSwapBind(RecordBind.getInstance());
        else RecordBind.getInstance().use();
    }

    private void handleCreateButton() {
        if (nameTextField.getText().isEmpty()) return;
        scriptsComboBox.getItems().add(nameTextField.getText());
        scriptsComboBox.getSelectionModel().select(nameTextField.getText());
        ActionStorageManager.getInstance().createStorage(nameTextField.getText());
        nameTextField.setText("");
    }

    private void handleRemoveButton() {
        String selected = scriptsComboBox.getSelectionModel().getSelectedItem();
        if (scriptsComboBox.getItems().size() < 2) return;
        scriptsComboBox.getItems().remove(selected);
        ActionStorageManager.getInstance().removeStorage(selected);
        scriptsComboBox.getSelectionModel().select(0);
    }

    private void handleSwapBind(Bind bind) {
        Pane pane = new Pane();
        pane.getStyleClass().add("dialog-pane");
        pane.setPrefWidth(600);
        pane.setPrefHeight(400);
        Label label = new Label("Press key to swap");
        label.setLayoutX(200);
        label.setLayoutY(100);
        label.getStyleClass().add("loading-big-label");
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.ESCAPE) BindUtil.getInstance().switchBind(bind, event.getCode().getCode());
            stage.close();
        });
    }


}
