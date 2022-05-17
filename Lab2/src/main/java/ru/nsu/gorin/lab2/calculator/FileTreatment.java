package ru.nsu.gorin.lab2.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

import static ru.nsu.gorin.lab2.calculator.Constants.DELIMETER;

/**
 * Класс обработки данных входного файла
 */
public class FileTreatment {
    private static final Logger logger = LogManager.getLogger(FileTreatment.class);

    /**
     * Метод построчно считывает данные с файла и парсит их, разбивая на "команду" и ее аргументы
     * Вызывает метод parseArgs класса ParseArguments для разделения команды и аргументов (если имеются)
     * @param context Экземпляр класса BaseContext, в котором хранятся стэк, мапа и методы для работы с ними
     * @see BaseContext
     * @see ParseArguments
     */
    public static void parseFile(BaseContext context) throws Exception {
        logger.info("method got the arguments to start parsing input file: " + context);

        Factory factory = new Factory();

        File fin = new File(context.getFinName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fin));
            String curStr = reader.readLine();
            if (curStr == null) {
                logger.info("Current string is already empty");
            }
            else {
                logger.info("Current command with arguments: " + curStr);
            }
            while (curStr != null) {
                String[] arguments = curStr.split(DELIMETER);

                ParseArguments.parseArgs(arguments, context, factory);

                curStr = reader.readLine();
            }
        } catch (IOException ex) {
            logger.error("File reading error: ", ex);
        }
    }
}
