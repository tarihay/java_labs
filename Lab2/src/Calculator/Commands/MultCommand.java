package Calculator.Commands;

import Calculator.BaseContext;
import Calculator.Exceptions.CommandArgsAmountException;

import java.util.EmptyStackException;

import static Calculator.Constants.DEFAULT;

/**
 * Класс команды '*' стэкового калькулятора, имплементирующий Worker
 * @see Calculator.Commands.Worker
 * @see Calculator.Commands.MultCommand#execute(BaseContext, String[])
 */
public class MultCommand implements Worker {
    private static final int ARGS_COUNT = 0;

    /**
     * Метод реализует перемножение двух верхних элементов стэка. Результат возвращается на стэк
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see Calculator.BaseContext
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
            ex.printStackTrace();
        }

        context.push(num1 * num2);
    }
}
