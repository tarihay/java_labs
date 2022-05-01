package ru.nsu.gorin.lab3.model;

import java.util.Random;
import static ru.nsu.gorin.lab3.Constants.*;

public class Field {
    private int height;
    private int width;
    private int[][] field;

    private int minesAmount;

    public Field(int height, int width, int minesAmount) {
        field = new int[width][height];
        this.height = height;
        this.width = width;

        this.minesAmount = minesAmount;

        fillTheFieldByRandom(minesAmount);
    }

    private void fillTheFieldByRandom(int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            if (field[randomX][randomY] == MINE) {
                fillTheFieldByRandom(ONE_TIME);
            }
            else {
                field[randomX][randomY] = MINE;
                countMineCounters(randomX, randomY);
            }
        }
    }

    private void countMineCounters(int x, int y) {
        final int RIGHT_BORDER = width - 1;
        final int LEFT_BORDER = 0;
        final int LOWER_BORDER = height - 1;
        final int UPPER_BORDER = 0;
        if (x == LEFT_BORDER) {
            if (y == UPPER_BORDER) {
                field[x+1][y+1]++;
                field[x+1][y]++;
                field[x][y+1]++;
            }
            else if (y == LOWER_BORDER) {
                field[x+1][y-1]++;
                field[x+1][y]++;
                field[x][y-1]++;
            }
            else {
                field[x+1][y+1]++;
                field[x+1][y]++;
                field[x][y+1]++;
                field[x][y-1]++;
                field[x+1][y-1]++;
            }
        }
        else if (x == RIGHT_BORDER) {
            if (y == UPPER_BORDER) {
                field[x-1][y+1]++;
                field[x-1][y]++;
                field[x][y+1]++;
            }
            else if (y == LOWER_BORDER) {
                field[x-1][y-1]++;
                field[x-1][y]++;
                field[x][y-1]++;
            }
            else {
                field[x-1][y+1]++;
                field[x-1][y]++;
                field[x][y+1]++;
                field[x][y-1]++;
                field[x-1][y-1]++;
            }
        }
        else if (y == UPPER_BORDER) {
            field[x+1][y+1]++;
            field[x-1][y]++;
            field[x][y+1]++;
            field[x+1][y]++;
            field[x-1][y+1]++;
        }
        else if (y == LOWER_BORDER) {
            field[x+1][y-1]++;
            field[x-1][y]++;
            field[x][y-1]++;
            field[x+1][y]++;
            field[x-1][y-1]++;
        }
        else {
            field[x+1][y+1]++;
            field[x+1][y-1]++;
            field[x-1][y+1]++;
            field[x-1][y-1]++;
            field[x+1][y]++;
            field[x-1][y]++;
            field[x][y+1]++;
            field[x][y-1]++;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getValue(int y, int x) {
        return field[y][x];
    }

    public void setNonActive(int y, int x) {
        field[y][x] = NON_ACTIVE;
    }

    public void setFlag(int x, int y) {
        field[x][y] += FLAG_VALUE;
    }

    public void removeFlag(int x, int y) {
        field[x][y] -= FLAG_VALUE;
    }

    public int getMinesAmount() {
        return minesAmount;
    }
}
