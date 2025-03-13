package dev.knalis.xsao.utils.windows;

import dev.knalis.xsao.interfaces.ISceneLoader;
import dev.knalis.xsao.xSao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import static javafx.scene.paint.Color.TRANSPARENT;

@AllArgsConstructor
public class SceneLoader implements ISceneLoader {
    @NonNull
    private final String fxmlPath;
    private final Stage stage;

    @Override
    public void load() {
        FXMLLoader loaderMain = new FXMLLoader(xSao.class.getResource(fxmlPath));
        try {
            Scene scene = new Scene(loaderMain.load());
            scene.setFill(TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load scene", e);
        }
    }
}
