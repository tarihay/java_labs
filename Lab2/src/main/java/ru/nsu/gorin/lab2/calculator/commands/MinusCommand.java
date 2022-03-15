package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

import static ru.nsu.gorin.lab2.calculator.Constants.DEFAULT;

/**
 * Класс команды '-' стэкового калькулятора, имплементирующий Worker
 * @see Worker
 * @see MinusCommand#execute(BaseContext, String[])
 */
public class MinusCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(MinusCommand.class);

    private static final int ARGS_COUNT = 0;

    /**
     * Метод реализует вычитание двух верхних элементов стэка. Результат возвращается на стэк
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }
        double num1 = DEFAULT;
        double num2 = DEFAULT;
        try {
            num1 = context.popReturn();
            num2 = context.popReturn();
            context.push(num2 - num1);
        }
        catch (EmptyStackException ex) {
            logger.error("There is an empty stack: ", ex);
        }
    }
}
