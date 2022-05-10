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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab3.model.TemplateTimer;
import ru.nsu.gorin.lab3.model.TimerListener;

import java.io.IOException;

import static ru.nsu.gorin.lab3.Constants.*;

/**
 * Класс-контроллер окна подготовки к игре
 * Вызывается после нажатия кнопки "PLAY" в меню игры
 * @see MenuWindowController
 */
public class GamePrepWindowController {
    private static final Logger logger = LogManager.getLogger(GamePrepWindowController.class);

    private final static String STANDARD_NAME = "Nick";

    @FXML private GameWindowController gameWindowController;


    private boolean isYNumCorrect = true;
    private boolean isXNumCorrect = true;
    private boolean isMineNumCorrect = true;

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
    private Text checkCorrectnessText;

    /**
     * Метод устанавливает действие при нажатии на конкретную кнопку
     */
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
                logger.error(e);
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
                    isYNumCorrect = false;
                    logger.warn("Check fieldY correctness");
                }
                try {
                    fieldX = Integer.parseInt(widthField.getText());
                }
                catch (Exception ex) {
                    isXNumCorrect = false;
                    logger.warn("Check fieldX correctness");
                }
                try {
                    mineCount = Integer.parseInt(minesField.getText());
                }
                catch (Exception ex) {
                    isMineNumCorrect = false;
                    logger.warn("Check mineCount correctness");
                }
            }

            if (fieldY > HEIGHT_MAX || fieldY < HEIGHT_MIN) {
                isYNumCorrect = false;
            }
            else {
                isYNumCorrect = true;
            }
            if (fieldX > WIDTH_MAX || fieldX < WIDTH_MIN) {
                isXNumCorrect = false;
            }
            else {
                isXNumCorrect = true;
            }
            if (mineCount > MINE_MAX || mineCount < MINE_MIN) {
                isMineNumCorrect = false;
            }
            else {
                isMineNumCorrect = true;
            }

            if (nickField.getText().isEmpty()) {
                nick = STANDARD_NAME;
            }
            else {
                nick = nickField.getText();
            }

            if (isYNumCorrect && isXNumCorrect && isMineNumCorrect) {
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_GAME_WINDOW_NAME));
                Parent rootNode = null;
                try {
                    rootNode = fxmlLoader.load();
                } catch (IOException e) {
                    logger.error(e);
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
                        logger.info("Timer was completely shutdowned");
                    }
                });
                stage.show();
            }
            else {
                checkCorrectnessText.setVisible(true);
            }
        });
    }

    /**
     * Хэндлер нажатия на кнопку "easy"
     */
    public void handleEasyBox() {
        if (easyBox.isSelected()) {
            normalBox.setSelected(false);
            hardBox.setSelected(false);
            customBox.setSelected(false);

            hBoxCustom.setVisible(false);
        }
    }
    /**
     * Хэндлер нажатия на кнопку "normal" (он же medium)
     */
    public void handleNormalBox() {
        if (normalBox.isSelected()) {
            easyBox.setSelected(false);
            hardBox.setSelected(false);
            customBox.setSelected(false);

            hBoxCustom.setVisible(false);
        }
    }
    /**
     * Хэндлер нажатия на кнопку "hard"
     */
    public void handleHardBox() {
        if (hardBox.isSelected()) {
            easyBox.setSelected(false);
            normalBox.setSelected(false);
            customBox.setSelected(false);

            hBoxCustom.setVisible(false);
        }
    }
    /**
     * Хэндлер нажатия на кнопку "custom"
     */
    public void handleCustomBox() {
        if (customBox.isSelected()) {
            easyBox.setSelected(false);
            normalBox.setSelected(false);
            hardBox.setSelected(false);

            hBoxCustom.setVisible(true);
        }
    }
}
