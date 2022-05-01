package ru.nsu.gorin.lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static ru.nsu.gorin.lab3.Constants.*;

public class MenuWindowApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MENU_NAME));
        Scene scene = new Scene(fxmlLoader.load(), MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_PATH)));
        stage.setTitle(GAME_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
