package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

import static ru.nsu.gorin.lab2.calculator.Constants.DEFAULT;

/**
 * Класс команды SQRT стэкового калькулятора, имплементирующий Worker
 * @see Worker
 * @see SqrtCommand#execute(BaseContext, String[])
 */
public class SqrtCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(SqrtCommand.class);

    private static final int ARGS_COUNT = 0;

    /**
     * Метод вычисляет квадратный корень верхнего на стэке числа и кладет результат обратно
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }
        double num = DEFAULT;
        try {
            num = context.popReturn();
            num = Math.sqrt(num);
            context.push(num);
        }
        catch (EmptyStackException ex) {
            logger.error("There is an empty stack: ", ex);
        }
    }
}
