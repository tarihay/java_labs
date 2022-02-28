package Calculator.Commands;

import Calculator.BaseContext;
import Calculator.Exceptions.CommandArgsAmountException;

import java.util.EmptyStackException;

import static Calculator.Constants.DEFAULT;

/**
 * Класс команды SQRT стэкового калькулятора, имплементирующий Worker
 * @see Calculator.Commands.Worker
 * @see Calculator.Commands.SqrtCommand#execute(BaseContext, String[])
 */
public class SqrtCommand implements Worker {
    private static final int ARGS_COUNT = 0;

    /**
     * Метод вычисляет квадратный корень верхнего на стэке числа и кладет результат обратно
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see Calculator.BaseContext
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
            ex.printStackTrace();
        }

        num = Math.sqrt(num);
        context.push(num);
    }
}
