package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.exceptions.CommandArgsAmountException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Objects;

import static ru.nsu.gorin.lab2.calculator.Constants.FILE_METHOD;
import static ru.nsu.gorin.lab2.calculator.Constants.FLOAT_DIFFERENCE;

/**
 * Класс команды PRINT стэкового калькулятора, имплементирующий Worker
 * @see Worker
 * @see PrintCommand#execute(BaseContext, String[])
 */
public class PrintCommand implements Worker {
    private static final Logger logger = LogManager.getLogger(PrintCommand.class);

    private static final int ARGS_COUNT = 0;

    /**
     * Метод выводит в консоль верхний элемент стэка из context
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     * @see BaseContext
     */
    @Override
    public void execute(BaseContext context, String[] arguments) throws Exception {
        if (arguments.length != ARGS_COUNT) {
            throw new CommandArgsAmountException("Wrongs amount of parameters");
        }

        try {
            double peekNum = context.peek();
            int intPeekNum = (int) peekNum;

            if (Objects.equals(context.getInputMethod(), FILE_METHOD)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(context.getFout(), true))) {
                    if (peekNum - intPeekNum == FLOAT_DIFFERENCE) {
                        writer.write(intPeekNum);
                    }
                    else {
                        writer.write(String.valueOf(peekNum));
                    }
                }
                catch (IOException ex) {
                    logger.error("Writer was opened unsuccessfully: ", ex);
                }
            }
            else {
                if (peekNum - intPeekNum == FLOAT_DIFFERENCE) {
                    System.out.println(intPeekNum);
                }
                else {
                    System.out.println(peekNum);
                }
            }
        }
        catch (EmptyStackException ex) {
            logger.error("There is an empty stack: ", ex);
        }
    }
}
