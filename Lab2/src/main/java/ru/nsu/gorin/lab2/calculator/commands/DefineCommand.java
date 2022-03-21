package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ru.nsu.gorin.lab2.calculator.Constants.*;

/**
 * Класс команды DEFINE стэкового калькулятора, имплементирующий Worker
 * @see Worker
 * @see DefineCommand#execute(BaseContext, String[])
 */
public class DefineCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(DefineCommand.class);

    private static final int ARGS_COUNT = 2;

    /**
     * Метод создает define в мапе со строкой-ключом и числом-значением
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see BaseContext
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
            context.define(name, num);
        }
        catch (NumberFormatException ex) {
            logger.error("Parsing failed: ", ex);
        }
    }
}
