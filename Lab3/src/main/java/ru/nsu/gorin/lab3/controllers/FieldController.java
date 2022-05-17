package ru.nsu.gorin.lab3.controllers;

import ru.nsu.gorin.lab3.model.Field;

import static ru.nsu.gorin.lab3.Constants.*;
import static ru.nsu.gorin.lab3.Constants.MINE_MIN;

public class FieldController {
    private Field field;

    private int fieldX;
    private int fieldY;
    private int mineCount;

    private boolean isYNumCorrect = true;
    private boolean isXNumCorrect = true;
    private boolean isMineNumCorrect = true;

    public void checkDataCorrectness() {
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
    }

    public boolean areFieldNumbersCorrect() {
        return isYNumCorrect && isXNumCorrect && isMineNumCorrect;
    }

    public boolean isYNumCorrect() {
        return isYNumCorrect;
    }
    public void setYNumCorrect(boolean YNumCorrect) {
        isYNumCorrect = YNumCorrect;
    }

    public boolean isXNumCorrect() {
        return isXNumCorrect;
    }
    public void setXNumCorrect(boolean XNumCorrect) {
        isXNumCorrect = XNumCorrect;
    }

    public boolean isMineNumCorrect() {
        return isMineNumCorrect;
    }
    public void setMineNumCorrect(boolean mineNumCorrect) {
        isMineNumCorrect = mineNumCorrect;
    }

    public int getFieldX() {
        return fieldX;
    }
    public int getFieldY() {
        return fieldY;
    }
    public int getMineCount() {
        return mineCount;
    }

    public void setAllData(int fieldY, int fieldX, int mineCount) {
        this.fieldY = fieldY;
        this.fieldX = fieldX;
        this.mineCount = mineCount;

        field = new Field(fieldY, fieldX, mineCount);
    }

    public Field getField() {
        return field;
    }

    public void removeFlag(int x, int y) {
        field.removeFlag(x, y);
    }

    public void setNonActive(int x, int y) {
        field.setNonActive(x, y);
    }

    public void changeOneMinePosition(int x, int y) {
        field.changeOneMinePosition(x, y);
    }
}
