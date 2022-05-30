package ru.nsu.gorin.lab3.viewControllers;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab3.controllers.FieldController;
import ru.nsu.gorin.lab3.controllers.StatsFileController;
import ru.nsu.gorin.lab3.model.Field;
import ru.nsu.gorin.lab3.model.TemplateTimer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.nsu.gorin.lab3.Constants.*;
import static ru.nsu.gorin.lab3.Constants.FLAG_PATH;

/**
 * Класс-контроллер окна игры
 * Открывается после нажатия на кнопку "PLAY" в окне подготовки к игре
 * @see GamePrepWindowController
 */
public class GameWindowController {
    private static final Logger logger = LogManager.getLogger(GameWindowController.class);

    private static final String AGAIN_TEXT = "Again";

    private FieldController fieldController;
    private final StatsFileController statsFileController = new StatsFileController();

    private boolean defeatStatus = false;
    private boolean winStatus = false;

    private boolean isFirstMove = true;

    private String nickName;

    private Field field;
    private Integer flagCount;
    private int hitMinesCount = 0;

    private TemplateTimer templateTimer;

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

    /**
     * Метод устанавливает действие при нажатии на конкретную кнопку
     */
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
                logger.error(e);
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
                logger.error(e);
            }
            stage = new Stage();
            Scene scene = new Scene(rootNode, MENU_WINDOW_WIDTH, MENU_WINDOW_HEIGHT);
            stage.setTitle(GAME_NAME);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_PATH)));
            stage.setScene(scene);
            stage.show();
        });
    }

    /**
     * Метод регистрирует нажатие по игровому полю
     * @param event хранит в себе информацию о нажатии
     */
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
                logger.info("Click on cell (" + row + "," + column + ") handled");


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

                            fieldController.removeFlag(row, column);
                        }
                    }
                }
            }
        }
    }

    public void setTimer(TemplateTimer templateTimer) {
        this.templateTimer = templateTimer;
    }

    /**
     * Метод устанавливает время в Label, которое в данный момент на таймере
     * @param time время, которое ставит TimerListener
     * @see ru.nsu.gorin.lab3.model.TimerListener
     */
    public void setTimerLabel(String time) {
        timerLabel.setText(time);
    }

    /**
     * Метод заполняет всю информацию о поле, собранную в окне подготовки к игре
     * @param nickName ник игрока
     * @param heightDifference на сколько новое окно отличается по высоте от стандартного
     * @param widthDifference на сколько новое окно отличается по ширине от стандартного
     *
     * @see GamePrepWindowController
     */
    public void fillTheField(String nickName, double heightDifference, double widthDifference) {
        this.nickName = nickName;

        flagCount = fieldController.getMineCount();

        field = fieldController.getField();

        nickNameLabel.setText(nickName);
        flagNum.setText(flagCount.toString());
        changeRowsColumnsAmount(heightDifference, widthDifference);
    }

    public void setFieldController(FieldController fieldController) {
        this.fieldController = fieldController;
    }

    /**
     * Метод изменяет стандартное поле 9X9 на новое
     * @param heightDifference на сколько новое окно отличается по высоте от стандартного
     * @param widthDifference на сколько новое окно отличается по ширине от стандартного
     */
    private void changeRowsColumnsAmount(double heightDifference, double widthDifference) {
        if (heightDifference < 0) {
            borderPane.setPrefHeight(borderPane.getPrefHeight() + Math.abs(heightDifference) + LITTLE_FIELD_HEIGHT_OFFSET);
        }
        if (widthDifference < 0) {
            borderPane.setPrefWidth(borderPane.getPrefWidth() + Math.abs(widthDifference) + LITTLE_FIELD_WIDTH_OFFSET);
        }

        fieldPane.getChildren().clear();
        for (int x = 0; x < fieldController.getFieldX(); x++) {
            for (int y = 0; y < fieldController.getFieldY(); y++) {
                ImageView image = new ImageView(new Image(this.getClass().getResourceAsStream(BLOCK_PATH)));
                image.setFitWidth(BLOCK_WIDTH);
                image.setFitHeight(BLOCK_HEIGHT);
                fieldPane.add(image, x, y);
            }
        }

        fieldPane.setHgap(-1);
        fieldPane.setVgap(-1);

        logger.info("Field was completely changed");
    }

    /**
     * Метод открывает ячейку поля
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    private void openTheCell(int x, int y) {
        if (field.getValue(x, y) == NON_ACTIVE) {
            logger.info("Cell was already clicked");
            return;
        }
        int fieldValue = field.getValue(x, y);
        Image image = new Image(this.getClass().getResourceAsStream(FIELD_BLOCK_PATH));
        if (fieldValue > NOTHING && fieldValue < MINE) {
            setCorrectImage(fieldValue, x, y);
            fieldController.setNonActive(x, y);

            if(isFirstMove) {
                isFirstMove = false;
                openTheCellsByConditionIfNotAMine(x, y);
            }
        }
        else if (fieldValue == NOTHING){
            fieldPane.add(new ImageView(image), x, y);
            fieldController.setNonActive(x, y);
            if (isFirstMove) {
                isFirstMove = false;
                openTheCellsByConditionIfNotAMine(x, y);
            }
            else {
                openTheCellsByCondition(x, y);
            }
        }
        else if (fieldValue >= MINE){
            if (isFirstMove) {
                isFirstMove = false;
                fieldController.changeOneMinePosition(x, y);
                openTheCell(x, y);
                openTheCellsByConditionIfNotAMine(x, y);
            }
            else {
                image = new Image(this.getClass().getResourceAsStream(HITMINE_PATH));
                fieldPane.add(new ImageView(image), x, y);
                fieldController.setNonActive(x, y);

                showDefeat();
            }
        }
    }

    /**
     * Рекурсивно открывает ячейки поля
     * Вызывает новую ветку рекурсии в методе openTheCell
     *
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    private void openTheCellsByCondition(int x, int y) {
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

        if (isNotLeftBorder && isNotUpperBorder) {
            openTheCell(x-1, y-1);
        }
        if (isNotLeftBorder && isNotLowerBorder) {
            openTheCell(x-1, y+1);
        }
        if (isNotRightBorder && isNotUpperBorder) {
            openTheCell(x+1, y-1);
        }
        if (isNotRightBorder && isNotLowerBorder) {
            openTheCell(x+1, y+1);
        }
    }

    /**
     * Рекурсивно открывает ячейки поля в зависимости от того, есть в ячейке мина или нет
     * Вызывает новую ветку рекурсии в методе openTheCell
     * Вызывается только при первом ходе
     *
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    private void openTheCellsByConditionIfNotAMine(int x, int y) {
        int value;
        boolean isNotLeftBorder = x != 0;
        if (isNotLeftBorder) {
            value = field.getValue(x-1, y);
            if (value < BORDER_VALUE) {
                openTheCell(x-1, y);
            }
        }
        boolean isNotRightBorder = x != (field.getWidth() - 1);
        if (isNotRightBorder) {
            value = field.getValue(x+1, y);
            if (value < BORDER_VALUE) {
                openTheCell(x+1, y);
            }
        }
        boolean isNotUpperBorder = y != 0;
        if (isNotUpperBorder) {
            value = field.getValue(x, y-1);
            if (value < BORDER_VALUE) {
                openTheCell(x, y-1);
            }
        }
        boolean isNotLowerBorder = y != (field.getHeight() - 1);
        if (isNotLowerBorder) {
            value = field.getValue(x, y+1);
            if (value < BORDER_VALUE) {
                openTheCell(x, y+1);
            }
        }

        if (isNotLeftBorder && isNotUpperBorder) {
            value = field.getValue(x-1, y-1);
            if (value < BORDER_VALUE) {
                openTheCell(x-1, y-1);
            }
        }
        if (isNotLeftBorder && isNotLowerBorder) {
            value = field.getValue(x-1, y+1);
            if (value < BORDER_VALUE) {
                openTheCell(x-1, y+1);
            }
        }
        if (isNotRightBorder && isNotUpperBorder) {
            value = field.getValue(x+1, y-1);
            if (value < BORDER_VALUE) {
                openTheCell(x+1, y-1);
            }
        }
        if (isNotRightBorder && isNotLowerBorder) {
            value = field.getValue(x+1, y+1);
            if (value < BORDER_VALUE) {
                openTheCell(x+1, y+1);
            }
        }
    }

    /**
     * Метод устанавливает необходимую картинку в зависимости от того, какое значение стоит в поле
     * @param fieldValue значение поля
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
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

    /**
     * Метод устанавливает флаг в нужную ячейку
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    private void setFlag(int x, int y) {
        if (field.getValue(x, y) == NON_ACTIVE) {
            logger.info("Cell was already clicked");
            return;
        }
        if (field.getValue(x, y) >= FLAG_VALUE) {
            Image image = new Image(this.getClass().getResourceAsStream(BLOCK_PATH));
            fieldPane.add(new ImageView(image), x, y);
            flagCount++;
            flagNum.setText(Integer.toString(flagCount));

            fieldController.removeFlag(x, y);
        }
        else {
            Image image = new Image(this.getClass().getResourceAsStream(FLAG_PATH));
            fieldPane.add(new ImageView(image), x, y);
            flagCount--;
            flagNum.setText(Integer.toString(flagCount));

            if (field.getValue(x, y) >= MINE) {
                hitMinesCount++;
            }
            if (hitMinesCount == fieldController.getMineCount()) {
                showVictory();
            }
            field.setFlag(x, y);
        }
    }

    /**
     * Метод заканчивает игру победой
     */
    private void showVictory() {
        logger.info("Player " + nickName + " won the game");

        winText.setVisible(true);
        templateTimer.shutdown();
        statsFileController.writeResults(fieldController.getFieldY(), fieldController.getFieldY(), fieldController.getMineCount(),
                                        timerLabel.getText(), nickName);

        endGameChanges();

        winStatus = true;
    }

    /**
     * Метод заканчивает игру поражением
     */
    private void showDefeat() {
        logger.info("Player " + nickName + " lost");

        loseText.setVisible(true);
        showAllMines();
        templateTimer.shutdown();

        endGameChanges();

        defeatStatus = true;
    }


    /**
     * Метод показывает все оставшиеся неоткрытые мины
     * Вызывается в методе showDefeat()
     */
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

}
