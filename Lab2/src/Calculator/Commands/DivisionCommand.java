package Calculator.Commands;

import Calculator.BaseContext;
import Calculator.Exceptions.CommandArgsAmountException;

import java.util.EmptyStackException;

/**
 * Класс команды '/' стэкового калькулятора, имплементирующий Worker
 * @see Calculator.Commands.Worker
 * @see Calculator.Commands.DivisionCommand#execute(BaseContext, String[])
 */
public class DivisionCommand implements Worker{
    private static final double DEFAULT = 0.0;
    private static final int ARGS_COUNT = 0;

    /**
     * Метод реализует деление двух верхних элементов стэка. Результат возвращается на стэк
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception{
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
            ex.printStackTrace();
        }

        context.push(num2 / num1);
    }
}
