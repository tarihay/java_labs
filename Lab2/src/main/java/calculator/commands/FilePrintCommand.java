package calculator.commands;

import calculator.BaseContext;
import calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

import static calculator.Constants.FLOAT_DIFFERENCE;
import static calculator.Constants.NO_ARGS;

/**
 * Класс команды PRINT стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see StreamPrintCommand#execute(BaseContext, String[])
 */
public class FilePrintCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(FilePrintCommand.class);

    private static final int ARGS_COUNT = 0;

    /**
     * Метод выводит в выходной файл верхний элемент стэка из context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see calculator.BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parameters");
        }

        double peekNum = context.peek();
        int intPeekNum = (int) peekNum;

        try (FileWriter writer = new FileWriter(context.getFout(), true)) {
            if (peekNum - intPeekNum == FLOAT_DIFFERENCE) {
                writer.write(intPeekNum + "\n");
            }
            else {
                writer.write(peekNum + "\n");
            }
        }
        catch (IOException ex) {
            logger.error("Writer was opened unsuccessfully: ", ex);
        }
    }
}
