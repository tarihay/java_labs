package calculator.commands;

import calculator.BaseContext;
import calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;
import static calculator.Constants.DEFAULT;

/**
 * Класс команды '+' стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see calculator.commands.PlusCommand#execute(BaseContext, String[])
 */
public class PlusCommand implements Worker {
    private static final int ARGS_COUNT = 0;

    private static final Logger logger = LogManager.getLogger(PlusCommand.class);

    /**
     * Метод реализует сложение двух верхних элементов стэка. Результат возвращается на стэк
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see calculator.BaseContext
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
        }
        catch (EmptyStackException ex) {
            logger.error(ex.getMessage());
        }

        context.push(num1 + num2);
    }
}
