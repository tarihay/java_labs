package ru.nsu.gorin.lab2.calculator;

import ru.nsu.gorin.lab2.calculator.commands.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static ru.nsu.gorin.lab2.calculator.Constants.NO_ARGS;

/**
 * Класс с методом парсинга массива строк, вышедшего из FileTreatment или StreamInput. Зависит от метода ввода
 * @see ParseArguments#parseArgs(String[], BaseContext, Factory)
 * @see FileTreatment
 * @see StreamInput
 */
public class ParseArguments {
    private static final Logger logger = LogManager.getLogger(ParseArguments.class);

    private static final int FIRST_ARG = 1;
    private static final int COMMAND_POS = 0;
    private static final int ARRAY_START = 0;

    private static final char COMMENT = '#';

    /**
     * Метод обработки массива строк, вышедшего из вышедшего из FileTreatment или StreamInput. Зависит от метода ввода
     * Метод выделяет строку с командой и строку с аргументами, если таковые имеются
     * @param arguments массив строк, содержащий команду и аргументы (если имеются)
     * @param context Экземпляр класса BaseContext, в котором хранятся стэк, мапа и методы для работы с ними
     * @see FileTreatment
     * @see StreamInput
     * @see BaseContext
     */
    public static void parseArgs(String[] arguments, BaseContext context, Factory factory) throws Exception {
        if (arguments.length == NO_ARGS) {
            logger.error("No arguments given");
        }
        else {
            logger.info("Run parseArgs with context: \n" + context);
        }

        final int LENGTH_WITHOUT_COMMAND = arguments.length - 1;
        String command = arguments[COMMAND_POS];
        String[] additions = new String[LENGTH_WITHOUT_COMMAND];
        if (LENGTH_WITHOUT_COMMAND > NO_ARGS) {
            System.arraycopy(arguments, FIRST_ARG, additions, ARRAY_START, LENGTH_WITHOUT_COMMAND);
        }

        if (!Objects.equals(command.charAt(COMMAND_POS), COMMENT)) {
            Worker curCommand = factory.getCommand(command);
            if (curCommand != null) {
                curCommand.execute(context, additions);
            }
        }
    }
}
