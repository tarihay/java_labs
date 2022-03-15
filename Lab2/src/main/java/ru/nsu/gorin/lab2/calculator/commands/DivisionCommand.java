package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.exceptions.CommandArgsAmountException;
import ru.nsu.gorin.lab2.calculator.exceptions.DivisionByZeroException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

import static ru.nsu.gorin.lab2.calculator.Constants.DEFAULT;

/**
 * Класс команды '/' стэкового калькулятора, имплементирующий Worker
 * @see Worker
 * @see DivisionCommand#execute(BaseContext, String[])
 */
public class DivisionCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(DivisionCommand.class);

    private static final int ARGS_COUNT = 0;
    private static final int ZERO = 0;

    /**
     * Метод реализует деление двух верхних элементов стэка. Результат возвращается на стэк
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parameters");
        }


        double num1 = DEFAULT;
        double num2 = DEFAULT;
        try {
            num1 = context.popReturn();
            num2 = context.popReturn();
            if (num1 == ZERO) {
                logger.error("It could be division by zero. Be careful!");
            }
            else {
                context.push(num2 / num1);
            }
        }
        catch (EmptyStackException ex) {
            logger.error("There is an empty stack: ", ex);
        }
    }
}
