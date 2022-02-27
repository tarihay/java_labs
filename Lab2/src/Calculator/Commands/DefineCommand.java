package Calculator.Commands;

import Calculator.BaseContext;
import Calculator.Exceptions.CommandArgsAmountException;

/**
 * Класс команды DEFINE стэкового калькулятора, имплементирующий Worker
 * @see Calculator.Commands.Worker
 * @see Calculator.Commands.DefineCommand#execute(BaseContext, String[])
 */
public class DefineCommand implements Worker {
    private static final double DEFAULT = 0.0;
    private static final int FIRST_ARG = 0;
    private static final int SECOND_ARG = 1;
    private static final int ARGS_COUNT = 2;

    /**
     * Метод создает define в мапе со строкой-ключом и числом-значением
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }

        double num = DEFAULT;
        String name = arguments[FIRST_ARG];
        String value = arguments[SECOND_ARG];

        try {
            num = Double.parseDouble(value);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        context.define(name, num);
    }
}
