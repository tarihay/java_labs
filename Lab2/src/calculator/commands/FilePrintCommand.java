package calculator.commands;

import calculator.BaseContext;
import calculator.exceptions.CommandArgsAmountException;

import java.io.FileWriter;
import java.io.IOException;

import static calculator.Constants.FLOAT_DIFFERENCE;

/**
 * Класс команды PRINT стэкового калькулятора, имплементирующий Worker
 * @see calculator.commands.Worker
 * @see StreamPrintCommand#execute(BaseContext, String[])
 */
public class FilePrintCommand implements Worker{
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
            throw new CommandArgsAmountException("Wrongs amount of parametres");
        }

        double peekNum = context.peek();
        int intPeekNum = (int) peekNum;

        try (FileWriter writer = new FileWriter(context.getFoutName(), true)) {
            if (peekNum - intPeekNum == FLOAT_DIFFERENCE) {
                writer.write(intPeekNum + "\n");
            }
            else {
                writer.write(peekNum + "\n");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
