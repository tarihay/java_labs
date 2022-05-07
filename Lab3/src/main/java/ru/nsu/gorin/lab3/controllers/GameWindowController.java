package ru.nsu.gorin.lab3.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.nsu.gorin.lab3.model.Field;
import ru.nsu.gorin.lab3.model.TemplateTimer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Exchanger;

import static ru.nsu.gorin.lab3.Constants.*;
import static ru.nsu.gorin.lab3.Constants.FLAG_PATH;

public class GameWindowController {
    private static final String AGAIN_TEXT = "Again";

    boolean defeatStatus = false;
    boolean winStatus = false;

    private int fieldX;
    private int fieldY;
    private int mineCount;
    private String nickName;

    private Field field;
    private Integer flagCount;
    private int hitMinesCount = 0;

    private TemplateTimer templateTimer;
    private Exchanger<String> exchanger;

    private String time;

    @FXML
    private Label flagNum;
    @FXML
    private Label nickNameLabel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button backButton;
    @FXML
    private Button menuButton;

    @FXML
    private GridPane fieldPane;

    @FXML
    private Text winText;
    @FXML
    private Text loseText;

    @FXML
    private Label timerLabel;

    @FXML
    public void initialize() {
        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            templateTimer.shutdown();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_GAME_PREPARING_WINDOW_NAME));
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

        menuButton.setOnAction(event -> {
            Stage stage = (Stage) menuButton.getScene().getWindow();
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
    }

    @FXML
    public void paneClickHandler(MouseEvent event) {
        if (!winStatus && !defeatStatus) {
            Node node = fieldPane.getChildren().get(0);
            double y = node.getLayoutY();
            double x = node.getLayoutX();
            double xOffset = 0 - x;
            double yOffset = 0 - y;
            if (event.getPickResult().getIntersectedNode().getParent().
                    getParent().getClass().getName().equals("javafx.scene.layout.BorderPane")) {
                int row = (int) ((event.getPickResult().getIntersectedNode().getLayoutX()  + xOffset) / ACTUAL_BLOCK_WIDTH);
                int column = (int) ((event.getPickResult().getIntersectedNode().getLayoutY() + yOffset) / ACTUAL_BLOCK_HEIGHT);


                if (event.getButton() == MouseButton.PRIMARY) {
                    openTheCell(row, column);
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    if (flagCount > 0) {
                        setFlag(row, column);
                    }
                    else if (flagCount == 0) {
                        if (field.getValue(row, column) >= FLAG_VALUE) {
                            Image image = new Image(this.getClass().getResourceAsStream(BLOCK_PATH));
                            fieldPane.add(new ImageView(image), row, column);
                            flagCount++;
                            flagNum.setText(Integer.toString(flagCount));

                            field.removeFlag(row, column);
                        }
                    }
                }
            }
        }
    }

    public void setTimer(TemplateTimer templateTimer) {
        this.templateTimer = templateTimer;
    }

    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    public void fillTheField(int fieldY, int fieldX, int mineCount, String nickName,
                             double heightDifference, double widthDifference) {
        this.fieldY = fieldY;
        this.fieldX = fieldX;
        this.mineCount = mineCount;
        this.nickName = nickName;

        nickNameLabel.setText(nickName);

        flagCount = mineCount;
        flagNum.setText(flagCount.toString());

        field = new Field(fieldY, fieldX, mineCount);

        changeRowsColumnsAmount(heightDifference, widthDifference);
    }

    private void changeRowsColumnsAmount(double heightDifference, double widthDifference) {
        if (heightDifference < 0) {
            borderPane.setPrefHeight(borderPane.getPrefHeight() + Math.abs(heightDifference) + LITTLE_FIELD_HEIGHT_OFFSET);
        }
        if (widthDifference < 0) {
            borderPane.setPrefWidth(borderPane.getPrefWidth() + Math.abs(widthDifference) + LITTLE_FIELD_WIDTH_OFFSET);
        }

        fieldPane.getChildren().clear();
        for (int x = 0; x < fieldX; x++) {
            for (int y = 0; y < fieldY; y++) {
                ImageView image = new ImageView(new Image(this.getClass().getResourceAsStream(BLOCK_PATH)));
                image.setFitWidth(BLOCK_WIDTH);
                image.setFitHeight(BLOCK_HEIGHT);
                fieldPane.add(image, x, y);
            }
        }

        fieldPane.setHgap(-1);
        fieldPane.setVgap(-1);
    }

    private void openTheCell(int x, int y) {
        if (field.getValue(x, y) == NON_ACTIVE) {
            return;
        }
        int fieldValue = field.getValue(x, y);
        field.setNonActive(x, y);
        Image image = new Image(this.getClass().getResourceAsStream(FIELD_BLOCK_PATH));
        if (fieldValue > NOTHING && fieldValue < MINE) {
            setCorrectImage(fieldValue, x, y);
        }
        else if (fieldValue == NOTHING){
            fieldPane.add(new ImageView(image), x, y);
            boolean isNotLeftBorder = x != 0;
            if (isNotLeftBorder) {
                openTheCell(x-1, y);
            }
            boolean isNotRightBorder = x != (field.getWidth() - 1);
            if (isNotRightBorder) {
                openTheCell(x+1, y);
            }
            boolean isNotUpperBorder = y != 0;
            if (isNotUpperBorder) {
                openTheCell(x, y-1);
            }
            boolean isNotLowerBorder = y != (field.getHeight() - 1);
            if (isNotLowerBorder) {
                openTheCell(x, y+1);
            }
        }
        else if (fieldValue >= MINE){
            image = new Image(this.getClass().getResourceAsStream(HITMINE_PATH));
            fieldPane.add(new ImageView(image), x, y);

            showDefeat();
        }
    }

    private void setCorrectImage(int fieldValue, int x, int y) {
        Image image = new Image(this.getClass().getResourceAsStream(NUMBER_ONE_PATH));
        if (fieldValue == TWO_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_TWO_PATH));
        }
        if (fieldValue == THREE_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_THREE_PATH));
        }
        if (fieldValue == FOUR_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_FOUR_PATH));
        }
        if (fieldValue == FIVE_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_FIVE_PATH));
        }
        if (fieldValue == SIX_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_SIX_PATH));
        }
        if (fieldValue == SEVEN_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_SEVEN_PATH));
        }
        if (fieldValue == EIGHT_VALUE) {
            image = new Image(this.getClass().getResourceAsStream(NUMBER_EIGHT_PATH));
        }
        fieldPane.add(new ImageView(image), x, y);
    }

    private void setFlag(int x, int y) {
        if (field.getValue(x, y) == NON_ACTIVE) {
            return;
        }
        if (field.getValue(x, y) >= FLAG_VALUE) {
            Image image = new Image(this.getClass().getResourceAsStream(BLOCK_PATH));
            fieldPane.add(new ImageView(image), x, y);
            flagCount++;
            flagNum.setText(Integer.toString(flagCount));

            field.removeFlag(x, y);
        }
        else {
            Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
            fieldPane.add(new ImageView(image), x, y);
            flagCount--;
            flagNum.setText(Integer.toString(flagCount));

            if (field.getValue(x, y) >= MINE) {
                hitMinesCount++;
            }
            if (hitMinesCount == field.getMinesAmount()) {
                showVictory();
            }
            field.setFlag(x, y);
        }
    }

    private void showVictory() {
        winText.setVisible(true);
        templateTimer.shutdown();
        writeResults();

        endGameChanges();

        winStatus = true;
    }

    private void showDefeat() {
        loseText.setVisible(true);
        showAllMines();
        templateTimer.shutdown();

        endGameChanges();

        defeatStatus = true;
    }


    private void showAllMines() {
        Image image = new Image(this.getClass().getResourceAsStream(MINE_PATH));
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                int fieldValue = field.getValue(i, j);
                if (fieldValue >= MINE && fieldValue < FLAG_VALUE) {
                    fieldPane.add(new ImageView(image), i, j);
                }
            }
        }
    }

    private void endGameChanges() {
        backButton.setText(AGAIN_TEXT);
        menuButton.setVisible(true);
    }

    private void writeResults() {
        boolean isEasy = fieldY == EASY_FIELD_SIZE && fieldX == EASY_FIELD_SIZE && mineCount == EASY_MINES_AMOUNT;
        boolean isMedium = fieldY == MEDIUM_FIELD_SIZE && fieldX == MEDIUM_FIELD_SIZE && mineCount == MEDIUM_MINES_AMOUNT;
        boolean isHard = fieldY == HARD_FIELD_Y && fieldX == HARD_FIELD_X && mineCount == HARD_MINES_AMOUNT;

        File fout;
        if (isEasy) {
            fout = new File(EASY_RESULTS_PATH);
        }
        else if (isMedium) {
            fout = new File(MEDIUM_RESULTS_PATH);
        }
        else if (isHard) {
            fout = new File(HARD_RESULTS_PATH);
        }
        else {
            return;
        }

        try (FileWriter writer = new FileWriter(fout, false)) {
            String currentResult = timerLabel.getText() + " " + nickName + "\n";
            writer.write(currentResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
