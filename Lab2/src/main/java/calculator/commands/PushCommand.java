package calculator.commands;

import calculator.BaseContext;
import calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static calculator.Constants.*;

/**
 * Класс команды PUSH стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see calculator.commands.PushCommand#execute(BaseContext, String[])
 */
public class PushCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(PushCommand.class);

    private static final int ARGS_COUNT = 1;

    /**
     * Метод кладет аргумент команды на стэк в context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see calculator.BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }
        String curArg = arguments[FIRST_ARGUMENT];
        double num = DEFAULT;

        if (context.containsArg(curArg)) {
            num = context.getDefine(curArg);
            context.push(num);
        }
        else {
            try {
                num = Double.parseDouble(curArg);
                context.push(num);
            }
            catch (NumberFormatException ex) {
                logger.error("Parsing failed: ", ex);
            }
        }
    }
}
