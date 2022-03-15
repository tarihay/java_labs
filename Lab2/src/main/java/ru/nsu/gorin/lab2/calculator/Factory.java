package ru.nsu.gorin.lab2.calculator;

import ru.nsu.gorin.lab2.calculator.commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab2.calculator.commands.Worker;

import java.io.*;
import java.util.HashMap;

import static ru.nsu.gorin.lab2.calculator.Constants.DELIMETER;

/**
 * Класс реализации Фабрики команд калькулятора
 */
public class Factory {
    private static final Logger logger = LogManager.getLogger(Factory.class);

    private static final String COMMANDS_PATHS_TXT = "src/main/java/ru/nsu/gorin/lab2/calculator/CommandsPaths.txt";

    private static final int COMMAND_POS = 0;
    private static final int CLASS_PATH_POS = 1;

    private final HashMap<String, Worker> commands = new HashMap<>();

    /**
     * Создание фабрики, которая парсит файл с лежащими в ней командами и путями, записывая все в мапу
     */
    Factory() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(COMMANDS_PATHS_TXT));
            String line = reader.readLine();
            while (line != null) {
                String[] arguments = line.split(DELIMETER);

                Class<?> worker = Class.forName(arguments[CLASS_PATH_POS]);
                if (!commands.containsKey(arguments[COMMAND_POS])) {
                    commands.put(arguments[COMMAND_POS], (Worker) worker.newInstance());
                }
                else {
                    logger.warn("Don't try to rewrite already written command");
                }

                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            logger.error("File " + COMMANDS_PATHS_TXT + " was not found. Check the path: ", ex);
        } catch (IOException ex2) {
            logger.error("There is an input problem: ", ex2);
        } catch (ClassNotFoundException ex3) {
            logger.error("No such class found: ", ex3);
        } catch (IllegalAccessException | InstantiationException ex4) {
            logger.error("Access Error", ex4);
        }
    }

    /**
     * Возвращает экземпляр класса из мапы данной команды
     * @param command команда в String формате, которую нужно распознать
     * @return Возвращает экземпляр класса команды, если таковой имеется в мапе
     */
    public Worker getCommand(String command) {
        if (commands.containsKey(command)) {
            return commands.get(command);
        }
        else {
            logger.error("No such command found");
            return null;
        }
    }
}
