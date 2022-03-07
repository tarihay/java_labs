package calculator.commands;

import calculator.BaseContext;
import calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EmptyStackException;

import static calculator.Constants.FLOAT_DIFFERENCE;

/**
 * Класс команды PRINT стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see StreamPrintCommand#execute(BaseContext, String[])
 */
public class StreamPrintCommand implements Worker{
    private static final int ARGS_COUNT = 0;

    private static final Logger logger = LogManager.getLogger(StreamPrintCommand.class);

    /**
     * Метод выводит в консоль верхний элемент стэка из context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see calculator.BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }

        try {
            double peekNum = context.peek();
            int intPeekNum = (int) peekNum;
            if (peekNum - intPeekNum == FLOAT_DIFFERENCE) {
                System.out.println(intPeekNum);
            }
            else {
                System.out.println(peekNum);
            }
        }
        catch (EmptyStackException ex) {
            logger.error(ex.getMessage());
        }
    }
}
