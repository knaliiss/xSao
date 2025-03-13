package dev.knalis.xsao.controllers;

import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.binds.PlayBind;
import dev.knalis.xsao.utils.binds.RecordBind;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    Button closeButton, minimizeButton, recordButton, playButton, winButton, createButton;

    @FXML
    void initialize() {
        instance = this;
        initButtons();
        scriptsComboBox.getItems().addAll(ActionStorageManager.getInstance().getStorageNames());
        scriptsComboBox.getSelectionModel().select(0);
    }

    public String getSelectedScript() {
        return scriptsComboBox.getSelectionModel().getSelectedItem();
    }

    //TODO
    private void initButtons() {
        closeButton.setOnMouseClicked(event -> System.exit(0));
        minimizeButton.setOnMouseClicked(event -> ((Stage) minimizeButton.getScene().getWindow()).setIconified(true));
        recordButton.setOnMouseClicked(event -> RecordBind.getInstance().use());
        playButton.setOnMouseClicked(event -> PlayBind.getInstance().use());
//        winButton.setOnMouseClicked(event -> ActionStorageManager.getInstance().getStorage(getSelectedScript()).openWindow());
        createButton.setOnMouseClicked(event -> handleCreateButton());
    }

    private void handleCreateButton() {
        if (nameTextField.getText().isEmpty()) return;
        scriptsComboBox.getItems().add(nameTextField.getText());
        scriptsComboBox.getSelectionModel().select(nameTextField.getText());
        ActionStorageManager.getInstance().createStorage(nameTextField.getText());
        nameTextField.setText("");
    }


}
