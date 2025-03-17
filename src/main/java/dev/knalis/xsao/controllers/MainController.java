package dev.knalis.xsao.controllers;

import dev.knalis.xsao.utils.ActionStorageManager;
import dev.knalis.xsao.utils.WindowsManager;
import dev.knalis.xsao.utils.binds.PlayBind;
import dev.knalis.xsao.utils.binds.RecordBind;
import dev.knalis.xsao.utils.impl.ActionStorage;
import dev.knalis.xsao.utils.sound.SoundManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;

@Data
public class MainController {

    private final BindSwapper swapper = new BindSwapper();
    private int x, y;

    @Getter
    @Setter
    private static MainController instance;

    @FXML
    private Pane topPanel, backPain;

    @FXML
    private ComboBox<String> scriptsComboBox;

    @FXML
    private TextArea scriptTextArea;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button closeButton, minimizeButton, recordButton, playButton, winButton, createButton, removeButton;

    @FXML
    void initialize() {
        setInstance(this);
        initComboBox();
        initButtons();
        initTextArea();
        initTopPanel();
    }

    private void initTextArea() {
        scriptTextArea.setEditable(false);
        scriptTextArea.setWrapText(true);
        String selectedScript = scriptsComboBox.getSelectionModel().getSelectedItem();
        if (selectedScript != null) {
            scriptTextArea.setText(
                    ActionStorageManager.getInstance()
                            .getStorage(selectedScript)
                            .getList()
                            .toString()
            );
        }
    }

    private void initComboBox() {
        scriptsComboBox.getItems().addAll(ActionStorageManager.getInstance().getStorageNames());
        scriptsComboBox.getSelectionModel().selectFirst();
        scriptsComboBox.setOnAction(event -> {
            String selected = scriptsComboBox.getSelectionModel().getSelectedItem();
            if (selected != null) {
                ActionStorage storage = ActionStorageManager.getInstance().getStorage(selected);
                if (storage != null) {
                    scriptTextArea.setText(storage.getList().toString());
                } else {
                    System.err.println("Storage with name " + selected + " does not exist.");
                }
            }
        });
    }

    private void initTopPanel() {
        topPanel.setOnMousePressed(event -> {
            x = (int) event.getSceneX();
            y = (int) event.getSceneY();
        });
        topPanel.setOnMouseDragged(event -> {
            Stage stage = (Stage) topPanel.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    public String getSelectedScript() {
        return scriptsComboBox.getSelectionModel().getSelectedItem();
    }

    private void initButtons() {
        playButton.setText("Play [" + KeyEvent.getKeyText(PlayBind.getInstance().getKey()) + "]");
        recordButton.setText("Record [" + KeyEvent.getKeyText(RecordBind.getInstance().getKey()) + "]");

        closeButton.setOnMouseClicked(event -> System.exit(0));
        minimizeButton.setOnMouseClicked(event -> ((Stage) minimizeButton.getScene().getWindow()).setIconified(true));
        recordButton.setOnMouseClicked(this::handleRecordButton);
        playButton.setOnMouseClicked(this::handlePlayButton);
        winButton.setOnMouseClicked(event -> handleWinButton());
        createButton.setOnMouseClicked(event -> handleCreateButton());
        removeButton.setOnMouseClicked(event -> handleRemoveButton());
    }

    private void handleWinButton() {
        winButton.setDisable(true);
        WindowsManager.getInstance().setWinPicks(true);
        SoundManager.getInstance().playSound("win");
    }

    private void handlePlayButton(MouseEvent event) {
        if (event.getButton() == MouseButton.MIDDLE) swapper.swap(PlayBind.getInstance());
        else PlayBind.getInstance().use();
    }

    private void handleRecordButton(MouseEvent event) {
        if (event.getButton() == MouseButton.MIDDLE) swapper.swap(RecordBind.getInstance());
        else RecordBind.getInstance().use();
    }

    private void handleCreateButton() {
        String name = nameTextField.getText();
        if (name.isEmpty()) return;

        scriptsComboBox.getItems().add(name);
        ActionStorageManager.getInstance().createStorage(name);
        scriptsComboBox.getSelectionModel().select(name);
        nameTextField.clear();
    }

    private void handleRemoveButton() {
        String selected = scriptsComboBox.getSelectionModel().getSelectedItem();
        if (selected == null || scriptsComboBox.getItems().size() < 2) return;

        scriptsComboBox.getItems().remove(selected);
        ActionStorageManager.getInstance().removeStorage(selected);
        scriptsComboBox.getSelectionModel().selectFirst();
    }
}
