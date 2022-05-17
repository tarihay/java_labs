package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

/**
 * Класс команды Pop стэкового калькулятора, имплементирующий Worker
 * @see Worker
 * @see PopCommand#execute(BaseContext, String[])
 */
public class PopCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(PopCommand.class);

    private static final int ARGS_COUNT = 0;

    /**
     * Метод удаляет верхний элемент стэка из context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }
        try {
            context.popDelete();
        }
        catch (EmptyStackException ex) {
            logger.error("There is an empty stack: ", ex);
        }
    }
}
