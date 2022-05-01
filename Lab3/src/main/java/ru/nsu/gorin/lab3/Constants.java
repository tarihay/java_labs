package ru.nsu.gorin.lab3;

public class Constants {
    public static final String GAME_NAME = "Minesweeper";

    public static final String FXML_MENU_NAME = "controller/MenuWindow.fxml";
    public static final String FXML_MENU_NAME_FROM_CONTROLLER = "MenuWindow.fxml";
    public static final String FXML_GAME_WINDOW = "GameWindow.fxml";
    public static final String FXML_FAQ_NAME = "AboutWindow.fxml";

    public static final int MENU_WINDOW_WIDTH = 768;
    public static final int MENU_WINDOW_HEIGHT = 500;

    public static final int ONE_TIME = 1;

    public static final int BEGINNER_FIELD_SIZE = 9;
    public static final int STANDARD_FIELD_SIZE = 9;
    public static final int INTERMEDIATE_FIELD_SIZE = 16;

    public static final int PROFESSIONAL_FIELD_Y = 16;
    public static final int PROFESSIONAL_FIELD_X = 30;

    public static final int BEGINNER_MINES_AMOUNT = 10;
    public static final int INTERMEDIATE_MINES_AMOUNT = 40;
    public static final int PROFESSIONAL_MINES_AMOUNT = 99;

    public static final int BLOCK_HEIGHT = 25;
    public static final double ACTUAL_BLOCK_HEIGHT = 24;
    public static final int BLOCK_WIDTH = 25;
    public static final double ACTUAL_BLOCK_WIDTH = 24.0;

    public static final int FIELD_HEIGHT_OFFSET = 150;
    public static final int FIELD_WIDTH_OFFSET = 75;

    public static final int LITTLE_FIELD_HEIGHT_OFFSET = 50;
    public static final int LITTLE_FIELD_WIDTH_OFFSET = 25;

    public static final String ICON_PATH = "images/icon.png";
    public static final String ICON_CONTROLLER_PATH = "images/icon.png";
    public static final String MINE_PATH = "images/mine.png";
    public static final String HITMINE_PATH = "images/hitmine.png";
    public static final String BLOCK_PATH = "images/block.png";
    public static final String FIELD_BLOCK_PATH = "images/fieldBlock.png";
    public static final String FLAG_PATH = "images/flag.png";
    public static final String NUMBER_ONE_PATH = "images/number1.png";
    public static final String NUMBER_TWO_PATH = "images/number2.png";
    public static final String NUMBER_THREE_PATH = "images/number3.png";
    public static final String NUMBER_FOUR_PATH = "images/number4.png";
    public static final String NUMBER_FIVE_PATH = "images/number5.png";
    public static final String NUMBER_SIX_PATH = "images/number6.png";
    public static final String NUMBER_SEVEN_PATH = "images/number7.png";
    public static final String NUMBER_EIGHT_PATH = "images/number8.png";
    public static final String WRONGMINE_PATH = "images/wrongmine.png";

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
    public static final int FLAG_VALUE = 100;
    public static final int MINE = 50;
    public static final int FLAG_ON_MINE_VALUE = FLAG_VALUE + MINE;
}
