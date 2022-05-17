package ru.nsu.gorin.lab2.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Scanner;

/**
 * Класс обработки данных с потока с консоли
 */
public class StreamInput {
    private static final Logger logger = LogManager.getLogger(StreamInput.class);

    private static final String END_COMMAND = "END";
    private static final String DELIMETER = " ";

    /**
     * Метод построчно считывает данные с потока с консоли и парсит их, разбивая на "команду" и ее аргументы
     * Вызывает метод parseArgs класса ParseArguments для разделения команды и аргументов (если имеются)
     * @param context Экземпляр класса BaseContext, в котором хранятся стэк, мапа и методы для работы с ними
     * @see BaseContext
     * @see ParseArguments
     */
    public static void parseStream(BaseContext context) throws Exception {
        logger.info("stream parsing started successfully: \n" + context);

        Factory factory = new Factory();

        Scanner stream = new Scanner(System.in);
        String curStr = stream.nextLine();
        logger.info("Current command with arguments: " + curStr);
        while (!Objects.equals(curStr, END_COMMAND)) {
            String[] arguments = curStr.split(DELIMETER);

            ParseArguments.parseArgs(arguments, context, factory);

            curStr = stream.nextLine();
        }
    }
}
