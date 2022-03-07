package calculator.commands;

import calculator.BaseContext;
import calculator.Main;
import calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static calculator.Constants.FIRST_ARGUMENT;
import static calculator.Constants.SECOND_ARGUMENT;
import static calculator.Constants.DEFAULT;

/**
 * Класс команды DEFINE стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see calculator.commands.DefineCommand#execute(BaseContext, String[])
 */
public class DefineCommand implements Worker {
    private static final int ARGS_COUNT = 2;

    private static final Logger logger = LogManager.getLogger(DefineCommand.class);

    /**
     * Метод создает define в мапе со строкой-ключом и числом-значением
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see calculator.BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parameters");
        }

        double num = DEFAULT;
        String name = arguments[FIRST_ARGUMENT];
        String value = arguments[SECOND_ARGUMENT];

        try {
            num = Double.parseDouble(value);
        }
        catch (NumberFormatException ex) {
            logger.error("Parsing failed" + ex.getMessage());
        }
        context.define(name, num);
    }
}
