package ru.nsu.gorin.lab3.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab3.viewControllers.GamePrepWindowController;
import ru.nsu.gorin.lab3.viewControllers.GameWindowController;

import java.util.Random;
import static ru.nsu.gorin.lab3.Constants.*;

/**
 * Класс игрового поля
 * Создается в окне подготовки к игре
 * Используется в окне игры
 *
 * @see GamePrepWindowController
 * @see GameWindowController
 */
public class Field {
    private static final Logger logger = LogManager.getLogger(Field.class);

    private final int height;
    private final int width;
    private int[][] field;

    private int minesAmount;

    /**
     * Конструктор игрового поля
     * Устанавливает параметры и вызывает метод для рандомного заполнения мин в поле
     * @param height высота поля
     * @param width ширина поля
     * @param minesAmount количество мин
     */
    public Field(int height, int width, int minesAmount) {
        field = new int[height][width];
        this.height = height;
        this.width = width;

        this.minesAmount = minesAmount;

        fillTheFieldByRandom(minesAmount);
    }

    /**
     * Метод меняет позицию мины, если она была нажата на первом ходу
     * @param x коодината ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    public void changeOneMinePosition(int x, int y) {
        logger.info("Controller requested changing the position of mine on the first move");

        Random random = new Random();
        int randomX = x;
        int randomY = y;
        while (field[randomY][randomX] >= MINE) {
            randomX = random.nextInt(width);
            randomY = random.nextInt(height);
        }
        field[y][x] -= MINE;
        field[randomY][randomX] = MINE;
        removeMineCounters(x, y);
        countMineCounters(randomX, randomY);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getValue(int x, int y) {
        return field[y][x];
    }

    public int getMinesAmount() {
        return minesAmount;
    }

    /**
     * Ставит численное значение, означающее, что ячейка была открыта
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    public void setNonActive(int x, int y) {
        field[y][x] = NON_ACTIVE;
    }

    /**
     * Ставит численное значение флага в поле
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    public void setFlag(int x, int y) {
        field[y][x] += FLAG_VALUE;
    }

    /**
     * Убирает численное значение флага в поле
     * @param x координата ячейки по оси X
     * @param y координата ячейки по оси Y
     */
    public void removeFlag(int x, int y) {
        field[y][x] -= FLAG_VALUE;
    }

    /**
     * Метод рандомно расставляет мины по пол.
     * @param count количество мин
     */
    private void fillTheFieldByRandom(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            if (field[randomY][randomX] >= MINE) {
                fillTheFieldByRandom(ONE_TIME);
            }
            else {
                field[randomY][randomX] = MINE;
                countMineCounters(randomX, randomY);
            }
        }
    }

    /**
     * Метод подсчитывает значения вокруг поля, где стоит мина
     * @param x координата мины по оси X
     * @param y координата мины по оси Y
     */
    private void countMineCounters(int x, int y) {
        final int RIGHT_BORDER = width - 1;
        final int LEFT_BORDER = 0;
        final int LOWER_BORDER = height - 1;
        final int UPPER_BORDER = 0;
        if (x == LEFT_BORDER) {
            if (y == UPPER_BORDER) {
                field[y+1][x+1]++;
                field[y][x+1]++;
                field[y+1][x]++;
            }
            else if (y == LOWER_BORDER) {
                field[y-1][x+1]++;
                field[y][x+1]++;
                field[y-1][x]++;
            }
            else {
                field[y+1][x+1]++;
                field[y][x+1]++;
                field[y+1][x]++;
                field[y-1][x]++;
                field[y-1][x+1]++;
            }
        }
        else if (x == RIGHT_BORDER) {
            if (y == UPPER_BORDER) {
                field[y+1][x-1]++;
                field[y][x-1]++;
                field[y+1][x]++;
            }
            else if (y == LOWER_BORDER) {
                field[y-1][x-1]++;
                field[y][x-1]++;
                field[y-1][x]++;
            }
            else {
                field[y+1][x-1]++;
                field[y][x-1]++;
                field[y+1][x]++;
                field[y-1][x]++;
                field[y-1][x-1]++;
            }
        }
        else if (y == UPPER_BORDER) {
            field[y+1][x+1]++;
            field[y][x-1]++;
            field[y+1][x]++;
            field[y][x+1]++;
            field[y+1][x-1]++;
        }
        else if (y == LOWER_BORDER) {
            field[y-1][x+1]++;
            field[y][x-1]++;
            field[y-1][x]++;
            field[y][x+1]++;
            field[y-1][x-1]++;
        }
        else {
            field[y+1][x+1]++;
            field[y-1][x+1]++;
            field[y+1][x-1]++;
            field[y-1][x-1]++;
            field[y][x+1]++;
            field[y][x-1]++;
            field[y+1][x]++;
            field[y-1][x]++;
        }
    }

    /**
     * Метод убирает счетчик мины после ее удаления
     * @param x координата ячейки по оси X, где стояла мина
     * @param y координата ячейки по оси Y, где стояла мина
     */
    private void removeMineCounters(int x, int y) {
        final int RIGHT_BORDER = width - 1;
        final int LEFT_BORDER = 0;
        final int LOWER_BORDER = height - 1;
        final int UPPER_BORDER = 0;
        if (x == LEFT_BORDER) {
            if (y == UPPER_BORDER) {
                field[y+1][x+1]--;
                field[y][x+1]--;
                field[y+1][x]--;
            }
            else if (y == LOWER_BORDER) {
                field[y-1][x+1]--;
                field[y][x+1]--;
                field[y-1][x]--;
            }
            else {
                field[y+1][x+1]--;
                field[y][x+1]--;
                field[y+1][x]--;
                field[y-1][x]--;
                field[y-1][x+1]--;
            }
        }
        else if (x == RIGHT_BORDER) {
            if (y == UPPER_BORDER) {
                field[y+1][x-1]--;
                field[y][x-1]--;
                field[y+1][x]--;
            }
            else if (y == LOWER_BORDER) {
                field[y-1][x-1]--;
                field[y][x-1]--;
                field[y-1][x]--;
            }
            else {
                field[y+1][x-1]--;
                field[y][x-1]--;
                field[y+1][x]--;
                field[y-1][x]--;
                field[y-1][x-1]--;
            }
        }
        else if (y == UPPER_BORDER) {
            field[y+1][x+1]--;
            field[y][x-1]--;
            field[y+1][x]--;
            field[y][x+1]--;
            field[y+1][x-1]--;
        }
        else if (y == LOWER_BORDER) {
            field[y-1][x+1]--;
            field[y][x-1]--;
            field[y-1][x]--;
            field[y][x+1]--;
            field[y-1][x-1]--;
        }
        else {
            field[y+1][x+1]--;
            field[y-1][x+1]--;
            field[y+1][x-1]--;
            field[y-1][x-1]--;
            field[y][x+1]--;
            field[y][x-1]--;
            field[y+1][x]--;
            field[y-1][x]--;
        }
    }
}
