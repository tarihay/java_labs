package ru.nsu.gorin.lab3.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.nsu.gorin.lab3.model.TemplateTimer;
import ru.nsu.gorin.lab3.model.TimerListener;

import java.io.IOException;
import java.util.concurrent.Exchanger;

import static ru.nsu.gorin.lab3.Constants.*;

public class GamePrepWindowController {
    private final static String STANDARD_NAME = "Nick";

    @FXML private AnchorPane gameWindow;
    @FXML private GameWindowController gameWindowController;

    @FXML private MenuWindowController menu;

    private boolean isNumCorrect = true;

    private int fieldX;
    private int fieldY;
    private int mineCount;
    private String nick;

    @FXML
    private CheckBox customBox;
    @FXML
    private CheckBox easyBox;
    @FXML
    private CheckBox normalBox;
    @FXML
    private CheckBox hardBox;
    @FXML
    private HBox hBoxCustom;

    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;

    @FXML
    private TextField minesField;

    @FXML
    private TextField nickField;

    @FXML
    private Button playButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MENU_NAME));
            Parent rootNode = null;
            try {
                rootNode = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            Scene scene = new Scene(rootNode, MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);
            stage.setTitle(GAME_NAME);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_PATH)));
            stage.setScene(scene);
            stage.show();
        });

        playButton.setOnAction(event -> {
            if (easyBox.isSelected()) {
                fieldY = EASY_FIELD_SIZE;
                fieldX = EASY_FIELD_SIZE;
                mineCount = EASY_MINES_AMOUNT;
            }
            if (normalBox.isSelected()) {
                fieldY = MEDIUM_FIELD_SIZE;
                fieldX = MEDIUM_FIELD_SIZE;
                mineCount = MEDIUM_MINES_AMOUNT;
            }
            if (hardBox.isSelected()) {
                fieldY = HARD_FIELD_Y;
                fieldX = HARD_FIELD_X;
                mineCount = HARD_MINES_AMOUNT;
            }

            if (customBox.isSelected()) {
                try {
                    fieldY = Integer.parseInt(heightField.getText());
                }
                catch (Exception ex) {
                    isNumCorrect = false;
                }
                try {
                    fieldX = Integer.parseInt(widthField.getText());
                }
                catch (Exception ex) {
                    isNumCorrect = false;
                }
                try {
                    mineCount = Integer.parseInt(minesField.getText());
                }
                catch (Exception ex) {
                    isNumCorrect = false;
                }
            }

            if (nickField.getText().isEmpty()) {
                nick = STANDARD_NAME;
            }
            else {
                nick = nickField.getText();
            }

            //TODO сделать подсвечивание красным в области, где были введены неверные данные
            if (isNumCorrect) {
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_GAME_WINDOW_NAME));
                Parent rootNode = null;
                try {
                    rootNode = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = new Stage();

                double heightDifference = MENU_WINDOW_HEIGHT - (fieldY * BLOCK_HEIGHT + FIELD_HEIGHT_OFFSET);
                double widthDifference = MENU_WINDOW_WIDTH - (fieldX * BLOCK_WIDTH + FIELD_WIDTH_OFFSET);

                double newHeight = MENU_WINDOW_HEIGHT;
                double newWidth = MENU_WINDOW_WIDTH;

                boolean isHeightDifferenceNegative = heightDifference < 0;
                if (isHeightDifferenceNegative) {
                    newHeight = MENU_WINDOW_HEIGHT + Math.abs(heightDifference) + LITTLE_FIELD_HEIGHT_OFFSET;
                }

                boolean isWidthDifferenceNegative = widthDifference < 0;
                if (isWidthDifferenceNegative) {
                    newWidth = MENU_WINDOW_WIDTH + Math.abs(widthDifference) + LITTLE_FIELD_WIDTH_OFFSET;
                }

                gameWindowController = fxmlLoader.getController();
                gameWindowController.fillTheField(fieldY, fieldX, mineCount, nick, heightDifference, widthDifference);

                TemplateTimer templateTimer = new TemplateTimer();
                templateTimer.addListener(new TimerListener() {
                    @Override
                    public void onReadingChange() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                gameWindowController.setTimerLabel(templateTimer.getResult());
                            }
                        });
                    }
                });

                gameWindowController.setTimer(templateTimer);
                Scene scene = new Scene(rootNode, newWidth, newHeight);
                stage.setTitle(GAME_NAME);
                stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_PATH)));
                stage.setScene(scene);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        templateTimer.shutdown();
                    }
                });
                stage.show();
            }
        });
    }

    public void handleEasyBox() {
        if (easyBox.isSelected()) {
            normalBox.setSelected(false);
            hardBox.setSelected(false);
            customBox.setSelected(false);

            hBoxCustom.setVisible(false);
        }
    }
    public void handleNormalBox() {
        if (normalBox.isSelected()) {
            easyBox.setSelected(false);
            hardBox.setSelected(false);
            customBox.setSelected(false);

            hBoxCustom.setVisible(false);
        }
    }
    public void handleHardBox() {
        if (hardBox.isSelected()) {
            easyBox.setSelected(false);
            normalBox.setSelected(false);
            customBox.setSelected(false);

            hBoxCustom.setVisible(false);
        }
    }
    public void handleCustomBox() {
        if (customBox.isSelected()) {
            easyBox.setSelected(false);
            normalBox.setSelected(false);
            hardBox.setSelected(false);

            hBoxCustom.setVisible(true);
        }
    }

    private void changeTime(Exchanger<String> exchanger) throws InterruptedException {
        String result = new String();
        try {
            result = exchanger.exchange(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameWindowController.setTimerLabel(result);
    }
}
