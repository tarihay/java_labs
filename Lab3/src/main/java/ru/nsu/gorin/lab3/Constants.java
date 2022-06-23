package ru.nsu.gorin.lab3;

public class Constants {
    public static final String GAME_NAME = "Minesweeper";

    public static final int MENU_ID = 1;
    public static final int GAME_ID = 2;
    public static final int GAME_PREPARING_ID = 3;
    public static final int FAQ_ID = 4;
    public static final int STATS_ID = 5;
    public static final int WAIT_STATUS = 6;

    public static final int ABSOLUTE_TIME_MINIMUM = 0;
    public static final int PLACES_AMOUNT = 7;

    public static final String FXML_MENU_NAME = "/ru/nsu/gorin/lab3/view/MenuWindow.fxml";
    public static final String FXML_GAME_WINDOW_NAME = "/ru/nsu/gorin/lab3/view/GameWindow.fxml";
    public static final String FXML_GAME_PREPARING_WINDOW_NAME = "/ru/nsu/gorin/lab3/view/GamePreparingWindow.fxml";
    public static final String FXML_FAQ_NAME = "/ru/nsu/gorin/lab3/view/AboutWindow.fxml";
    public static final String FXML_STATS_NAME = "/ru/nsu/gorin/lab3/view/StatsWindow.fxml";

    public static final int MENU_WINDOW_WIDTH = 768;
    public static final int MENU_WINDOW_HEIGHT = 500;

    public static final int ONE_TIME = 1;

    public static final int EASY_FIELD_SIZE = 9;
    public static final int MEDIUM_FIELD_SIZE = 16;

    public static final int HARD_FIELD_Y = 16;
    public static final int HARD_FIELD_X = 30;

    public static final int EASY_MINES_AMOUNT = 10;
    public static final int MEDIUM_MINES_AMOUNT = 40;
    public static final int HARD_MINES_AMOUNT = 99;

    public static final int HEIGHT_MAX = 24;
    public static final int WIDTH_MAX = 30;
    public static final int MINE_MAX = 111;
    public static final int HEIGHT_MIN = 9;
    public static final int WIDTH_MIN = 9;
    public static final int MINE_MIN = 10;

    public static final int BLOCK_HEIGHT = 25;
    public static final double ACTUAL_BLOCK_HEIGHT = 24;
    public static final int BLOCK_WIDTH = 25;
    public static final double ACTUAL_BLOCK_WIDTH = 24.0;

    public static final int FIELD_HEIGHT_OFFSET = 150;
    public static final int FIELD_WIDTH_OFFSET = 75;

    public static final int LITTLE_FIELD_HEIGHT_OFFSET = 50;
    public static final int LITTLE_FIELD_WIDTH_OFFSET = 25;

    public static final int SEC_IN_MILLIS = 1000;

    public static final String ICON_PATH = "/ru/nsu/gorin/lab3/view/images/icon.png";
    public static final String MINE_PATH = "/ru/nsu/gorin/lab3/view/images/mine.png";
    public static final String HITMINE_PATH = "/ru/nsu/gorin/lab3/view/images/hitmine.png";
    public static final String BLOCK_PATH = "/ru/nsu/gorin/lab3/view/images/block.png";
    public static final String FIELD_BLOCK_PATH = "/ru/nsu/gorin/lab3/view/images/fieldBlock.png";
    public static final String FLAG_PATH = "/ru/nsu/gorin/lab3/view/images/flag.png";
    public static final String NUMBER_ONE_PATH = "/ru/nsu/gorin/lab3/view/images/number1.png";
    public static final String NUMBER_TWO_PATH = "/ru/nsu/gorin/lab3/view/images/number2.png";
    public static final String NUMBER_THREE_PATH = "/ru/nsu/gorin/lab3/view/images/number3.png";
    public static final String NUMBER_FOUR_PATH = "/ru/nsu/gorin/lab3/view/images/number4.png";
    public static final String NUMBER_FIVE_PATH = "/ru/nsu/gorin/lab3/view/images/number5.png";
    public static final String NUMBER_SIX_PATH = "/ru/nsu/gorin/lab3/view/images/number6.png";
    public static final String NUMBER_SEVEN_PATH = "/ru/nsu/gorin/lab3/view/images/number7.png";
    public static final String NUMBER_EIGHT_PATH = "/ru/nsu/gorin/lab3/view/images/number8.png";

    public static final String EASY_RESULTS_PATH = "src/main/resources/ru/nsu/gorin/lab3/view/results/easyResults.txt";
    public static final String MEDIUM_RESULTS_PATH = "src/main/resources/ru/nsu/gorin/lab3/view/results/mediumResults.txt";
    public static final String HARD_RESULTS_PATH = "src/main/resources/ru/nsu/gorin/lab3/view/results/hardResults.txt";

    public static final char SPACE = ' ';

    public static final int NOT_OK_STATUS = -1;
    public static final int SECONDS_IN_MINUTE = 60;

    public static final int NON_ACTIVE = -1;
    public static final int NOTHING = 0;
    public static final int ONE_VALUE = 1;
    public static final int TWO_VALUE = 2;
    public static final int THREE_VALUE = 3;
    public static final int FOUR_VALUE = 4;
    public static final int FIVE_VALUE = 5;
    public static final int SIX_VALUE = 6;
    public static final int SEVEN_VALUE = 7;
    public static final int EIGHT_VALUE = 8;
    public static final int BORDER_VALUE = 9;
    public static final int FLAG_VALUE = 100;
    public static final int MINE = 50;
    public static final int FLAG_ON_MINE_VALUE = FLAG_VALUE + MINE;

    public static final char TIME_DELIMITER = ':';
}
