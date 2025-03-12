package dev.knalis.xsao.controllers;

import dev.knalis.xsao.utils.Util;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.awt.*;

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
    TextField nameTextField;

    @FXML
    Button closeButton, minimizeButton, recordButton, playButton, winButton, createButton;

    @FXML
    void initialize() {
        instance = this;
        Util.getScripts().forEach((name, script) -> scriptsComboBox.getItems().add(name));
        scriptsComboBox.getSelectionModel().select(0);

    }
}
