package calculator.commands;

import calculator.BaseContext;
import calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

import static calculator.Constants.DEFAULT;

/**
 * Класс команды SQRT стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see calculator.commands.SqrtCommand#execute(BaseContext, String[])
 */
public class SqrtCommand implements Worker {
    private static final int ARGS_COUNT = 0;

    private static final Logger logger = LogManager.getLogger(SqrtCommand.class);

    /**
     * Метод вычисляет квадратный корень верхнего на стэке числа и кладет результат обратно
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see calculator.BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception{
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }
        double num = DEFAULT;
        try {
            num = context.popReturn();
        }
        catch (EmptyStackException ex) {
            logger.error(ex.getMessage());
        }

        num = Math.sqrt(num);
        context.push(num);
    }
}
