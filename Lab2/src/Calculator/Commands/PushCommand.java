package Calculator.Commands;

import Calculator.BaseContext;
import Calculator.Exceptions.CommandArgsAmountException;

/**
 * Класс команды PUSH стэкового калькулятора, имплементирующий Worker
 * @see Calculator.Commands.Worker
 * @see Calculator.Commands.PushCommand#execute(BaseContext, String[])
 */
public class PushCommand implements Worker{
    private static final double DEFAULT = 0.0;
    private static final int FIRST_ARG = 0;
    private static final int ARGS_COUNT = 1;

    /**
     * Метод кладет аргумент команды на стэк в context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }
        String curArg = arguments[FIRST_ARG];
        double num = DEFAULT;

        if (context.containsArg(curArg)) {
            num = context.getDefine(curArg);
        }
        else {
            try {
                num = Double.parseDouble(curArg);
            }
            catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }

        context.push(num);
    }
}
