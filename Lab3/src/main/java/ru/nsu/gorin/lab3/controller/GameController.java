package ru.nsu.gorin.lab3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.nsu.gorin.lab3.model.Field;

import java.io.IOException;

import static ru.nsu.gorin.lab3.Constants.*;
import static ru.nsu.gorin.lab3.Constants.FLAG_PATH;

public class GameController {
    boolean defeatStatus;
    boolean winStatus;
    private boolean isNumCorrect = true;

    private Field field;
    private int flagCount;
    private int hitMinesCount = 0;

    @FXML
    private Label flagNum;
    @FXML
    private Label nickNameLabel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label nameLabel;
    @FXML
    private Label nickLabel;

    @FXML
    private Button backButton;
    @FXML
    private Button backButton1;

    @FXML
    private HBox hBoxCustom;

    @FXML
    private CheckBox customBox;

    @FXML
    private CheckBox easyBox;

    @FXML
    private CheckBox hardBox;

    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;

    @FXML
    private TextField minesField;

    @FXML
    private TextField nickField;

    @FXML
    private CheckBox normalBox;

    @FXML
    private GridPane fieldPane;

    @FXML
    private Button playButton;

    @FXML
    private Text winText;
    @FXML
    private Text loseText;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            showMainMenu();
        });

        backButton1.setOnAction(event -> {
            showMainMenu();
        });

        playButton.setOnAction(event -> {
            int fieldY = 0;
            int fieldX = 0;
            int mineCount = 0;
            if (easyBox.isSelected()) {
                fieldY = BEGINNER_FIELD_SIZE;
                fieldX = BEGINNER_FIELD_SIZE;
                mineCount = BEGINNER_MINES_AMOUNT;
            }
            if (normalBox.isSelected()) {
                fieldY = INTERMEDIATE_FIELD_SIZE;
                fieldX = INTERMEDIATE_FIELD_SIZE;
                mineCount = INTERMEDIATE_MINES_AMOUNT;
            }
            if (hardBox.isSelected()) {
                fieldY = PROFESSIONAL_FIELD_Y;
                fieldX = PROFESSIONAL_FIELD_X;
                mineCount = PROFESSIONAL_MINES_AMOUNT;
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

            if (isNumCorrect) {
                field = new Field(fieldY, fieldX, mineCount);
            }

            if (nickField.getText().isEmpty()) {
                nickNameLabel.setText("Nick");
            }
            else {
                nickNameLabel.setText(nickField.getText());
            }

            flagCount = mineCount;
            flagNum.setText(Integer.toString(flagCount));

            changeRowsColumnsAmount(fieldY, fieldX);

            showGameWindow();
            fieldPane.setHgap(-1);
            fieldPane.setVgap(-1);

            winStatus = false;
            defeatStatus = false;
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
                }
            }
        }
    }

    private void showMainMenu() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MENU_NAME_FROM_CONTROLLER));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.setTitle(GAME_NAME);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showGameWindow() {
        hBoxCustom.setVisible(false);
        customBox.setVisible(false);
        easyBox.setVisible(false);
        hardBox.setVisible(false);
        heightField.setVisible(false);
        widthField.setVisible(false);
        minesField.setVisible(false);
        nickField.setVisible(false);
        normalBox.setVisible(false);
        nameLabel.setVisible(false);
        playButton.setVisible(false);
        backButton.setVisible(false);
        nickLabel.setVisible(false);


        borderPane.setVisible(true);
    }

    private void changeRowsColumnsAmount(int fieldY, int fieldX) {
        Window window = borderPane.getScene().getWindow();

        double windowHeight = window.getHeight();
        double windowWidth = window.getWidth();
        double heightDifference = windowHeight - (fieldY * BLOCK_HEIGHT + FIELD_HEIGHT_OFFSET);
        double widthDifference = windowWidth - (fieldX * BLOCK_WIDTH + FIELD_WIDTH_OFFSET);

        boolean isHeightDifferenceNegative = heightDifference < 0;
        if (isHeightDifferenceNegative) {
            window.setHeight(windowHeight + Math.abs(heightDifference) + LITTLE_FIELD_HEIGHT_OFFSET);
            borderPane.setPrefHeight(borderPane.getPrefHeight() + Math.abs(heightDifference) + LITTLE_FIELD_HEIGHT_OFFSET);
        }

        boolean isWidthDifferenceNegative = widthDifference < 0;
        if (isWidthDifferenceNegative) {
            window.setWidth(windowWidth + Math.abs(widthDifference) + LITTLE_FIELD_WIDTH_OFFSET);
            borderPane.setPrefWidth(borderPane.getPrefWidth() + Math.abs(widthDifference) + LITTLE_FIELD_WIDTH_OFFSET);
        }
        window.centerOnScreen();


        fieldPane.getChildren().clear();
        for (int x = 0; x < fieldX; x++) {
            for (int y = 0; y < fieldY; y++) {
                ImageView image = new ImageView(new Image(this.getClass().getResourceAsStream(BLOCK_PATH)));
                image.setFitWidth(BLOCK_WIDTH);
                image.setFitHeight(BLOCK_HEIGHT);
                fieldPane.add(image, x, y);
            }
        }
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

        winStatus = true;
    }

    private void showDefeat() {
        loseText.setVisible(true);
        showAllMines();

        defeatStatus = true;
    }
    private void showAllMines() {
        Image image = new Image(this.getClass().getResourceAsStream(MINE_PATH));
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                int fieldValue = field.getValue(i, j);
                if (fieldValue >= MINE && fieldValue < FLAG_VALUE) {
                    fieldPane.add(new ImageView(image), i, j);
                }
            }
        }
    }
}
