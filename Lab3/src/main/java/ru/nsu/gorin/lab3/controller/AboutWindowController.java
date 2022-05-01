package ru.nsu.gorin.lab3.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.application.HostServices;

import java.io.IOException;

import static ru.nsu.gorin.lab3.Constants.*;
import static ru.nsu.gorin.lab3.Constants.NUMBER_ONE_PATH;

public class AboutWindowController {
    @FXML
    private Button backButton;

    @FXML
    private ImageView blockButton1;
    @FXML
    private ImageView blockButton10;
    @FXML
    private ImageView blockButton2;
    @FXML
    private ImageView blockButton3;
    @FXML
    private ImageView blockButton4;
    @FXML
    private ImageView blockButton5;
    @FXML
    private ImageView blockButton6;
    @FXML
    private ImageView blockButton7;
    @FXML
    private ImageView blockButton8;
    @FXML
    private ImageView blockButton9;

    @FXML
    private Hyperlink link;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MENU_NAME_FROM_CONTROLLER));
            Parent root1 = null;
            try {
                root1 = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.setTitle(GAME_NAME);
            stage.setScene(new Scene(root1));
            stage.show();
        });

        blockButton1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Image image = new Image(this.getClass().getResourceAsStream(HITMINE_PATH));
                blockButton1.setImage(image);
                blockButton1.setOnMouseClicked(event2 -> doNothing());
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton1.setImage(image);
            }
        });

        blockButton2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                showEmptyFields();
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton2.setImage(image);
            }
        });

        blockButton3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                showEmptyFields();
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton3.setImage(image);
            }
        });

        blockButton4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                showEmptyFields();
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton4.setImage(image);
            }
        });

        blockButton5.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                showEmptyFields();
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton5.setImage(image);
            }
        });

        blockButton6.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                showEmptyFields();
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton6.setImage(image);
            }
        });

        blockButton7.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Image image = new Image(this.getClass().getResourceAsStream(HITMINE_PATH));
                blockButton7.setImage(image);
                blockButton7.setOnMouseClicked(event2 -> doNothing());
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton7.setImage(image);
            }
        });

        blockButton8.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Image image = new Image(this.getClass().getResourceAsStream(NUMBER_TWO_PATH));
                blockButton8.setImage(image);
                blockButton8.setOnMouseClicked(event2 -> doNothing());
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton8.setImage(image);
            }
        });

        blockButton9.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Image image = new Image(this.getClass().getResourceAsStream(HITMINE_PATH));
                blockButton9.setImage(image);
                blockButton9.setOnMouseClicked(event2 -> doNothing());
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton9.setImage(image);
            }
        });

        blockButton10.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Image image = new Image(this.getClass().getResourceAsStream(NUMBER_ONE_PATH));
                blockButton10.setImage(image);
                blockButton10.setOnMouseClicked(event2 -> doNothing());
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
                blockButton10.setImage(image);
            }
        });
    }

    private void doNothing() {}

    private void showEmptyFields() {
        Image image = new Image(this.getClass().getResourceAsStream(FIELD_BLOCK_PATH));
        blockButton3.setImage(image);
        blockButton4.setImage(image);
        blockButton5.setImage(image);
        blockButton3.setOnMouseClicked(event2 -> doNothing());
        blockButton4.setOnMouseClicked(event2 -> doNothing());
        blockButton5.setOnMouseClicked(event2 -> doNothing());

        Image image1 = new Image(this.getClass().getResourceAsStream(NUMBER_ONE_PATH));
        blockButton2.setImage(image1);
        blockButton2.setOnMouseClicked(event2 -> doNothing());

        blockButton6.setImage(image1);
        blockButton6.setOnMouseClicked(event2 -> doNothing());
    }
}
