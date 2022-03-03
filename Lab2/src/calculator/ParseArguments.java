package calculator;

import calculator.commands.Worker;

import java.util.Objects;

/**
 * Класс с методом парсинга массива строк, вышедшего из FileTreatment или StreamInput. Зависит от метода ввода
 * @see calculator.ParseArguments#parseArgs(String[], BaseContext)
 * @see calculator.FileTreatment
 * @see calculator.StreamInput
 */
public class ParseArguments {
    private static final int FIRST_ARG = 1;
    private static final int COMMAND_POS = 0;
    private static final int ARRAY_START = 0;
    private static final int NO_ARGS = 0;

    private static final char COMMENT = '#';

    /**
     * Метод обработки массива строк, вышедшего из вышедшего из FileTreatment или StreamInput. Зависит от метода ввода
     * Метод выделяет строку с командой и строку с аргументами, если таковые имеются
     * @param arguments массив строк, содержащий команду и аргументы (если имеются)
     * @param context Экземпляр класса BaseContext, в котором хранятся стэк, мапа и методы для работы с ними
     * @see calculator.FileTreatment
     * @see calculator.StreamInput
     * @see calculator.BaseContext
     */
    public static void parseArgs(String[] arguments, BaseContext context) throws Exception{
        final int LENGTH_WITHOUT_COMMAND = arguments.length - 1;
        String command = arguments[COMMAND_POS];
        String[] additions = new String[LENGTH_WITHOUT_COMMAND];
        if (LENGTH_WITHOUT_COMMAND > NO_ARGS) {
            System.arraycopy(arguments, FIRST_ARG, additions, ARRAY_START, LENGTH_WITHOUT_COMMAND);
        }

        if (!Objects.equals(command.charAt(COMMAND_POS), COMMENT)) {
            Worker factory = Factory.createFactory(context, command);
            factory.execute(context, additions);
        }
    }
}
