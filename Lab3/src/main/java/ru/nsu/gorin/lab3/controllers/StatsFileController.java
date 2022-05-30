package ru.nsu.gorin.lab3.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.nsu.gorin.lab3.Constants.*;
import static ru.nsu.gorin.lab3.Constants.PLACES_AMOUNT;

public class StatsFileController {
    private static final Logger logger = LogManager.getLogger(StatsFileController.class);

    /**
     * Метод записывает результаты в случае победы
     * Вызывается в методе showVictory()
     */
    public void writeResults(int fieldY, int fieldX, int mineCount, String time, String nick) {
        boolean isEasy = fieldY == EASY_FIELD_SIZE && fieldX == EASY_FIELD_SIZE && mineCount == EASY_MINES_AMOUNT;

        boolean isMedium = fieldY == MEDIUM_FIELD_SIZE && fieldX == MEDIUM_FIELD_SIZE && mineCount == MEDIUM_MINES_AMOUNT;

        boolean isHard = fieldY == HARD_FIELD_Y && fieldX == HARD_FIELD_X && mineCount == HARD_MINES_AMOUNT;

        File file;
        String currentResult = time + " " + nick;
        if (isEasy) {
            file = new File(EASY_RESULTS_PATH);
        }
        else if (isMedium) {
            file = new File(MEDIUM_RESULTS_PATH);
        }
        else if (isHard) {
            file = new File(HARD_RESULTS_PATH);
        }
        else {
            return;
        }

        List<String> list = changeTopPlaces(file, currentResult);

        try (FileWriter writer = new FileWriter(file, false)) {
            int i = 0;
            for (String iterator : list) {
                writer.write(iterator + "\n");
                i++;
                if (i > PLACES_AMOUNT) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * Метод записывает в список все значения из файла и добавляет к ним текущий результат
     * @param file файл считывания
     * @param currentResult текущий результат
     * @return возвращает получившийся лист
     */
    private List<String> changeTopPlaces(File file, String currentResult) {
        List<String> list = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            logger.error(e);
        }

        list.add(currentResult);
        list = list.stream().sorted().collect(Collectors.toList());
        return list;
    }

}
