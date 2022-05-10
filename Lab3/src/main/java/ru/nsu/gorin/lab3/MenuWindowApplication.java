package ru.nsu.gorin.lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static ru.nsu.gorin.lab3.Constants.*;

/**
 * Основной класс приложения
 */
public class MenuWindowApplication extends Application {

    private Parent rootNode;

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MENU_NAME));
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(rootNode, MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);
        stage.setTitle(GAME_NAME);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_PATH)));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
