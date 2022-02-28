package Calculator.Commands;

import Calculator.BaseContext;
import Calculator.Exceptions.CommandArgsAmountException;

import java.util.EmptyStackException;

/**
 * Класс команды Pop стэкового калькулятора, имплементирующий Worker
 * @see Calculator.Commands.Worker
 * @see Calculator.Commands.PopCommand#execute(BaseContext, String[])
 */
public class PopCommand implements Worker {
    private static final int ARGS_COUNT = 0;

    /**
     * Метод удаляет верхний элемент стэка из context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see Calculator.BaseContext
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
            ex.printStackTrace();
        }
    }
}
