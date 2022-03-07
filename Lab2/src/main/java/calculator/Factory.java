package calculator;

import calculator.commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static calculator.Constants.FILE_METHOD;

/**
 * Класс реализации Фабрики команд калькулятора
 */
public class Factory {

    /**
     * Метод возвращает реализацию по указанной команде
     *
     * @param command - имя команды
     */
    public static Worker createFactory(BaseContext context, String command) throws ClassNotFoundException {
        switch (command) {
            case "POP" -> {
                return new PopCommand();
            }
            case "PUSH" -> {
                return new PushCommand();
            }
            case "+" -> {
                return new PlusCommand();
            }
            case "-" -> {
                return new MinusCommand();
            }
            case "*" -> {
                return new MultCommand();
            }
            case "/" -> {
                return new DivisionCommand();
            }
            case "PRINT" -> {
                String inputMethod = context.getInputMethod();
                if (Objects.equals(inputMethod, FILE_METHOD)) {
                    return new FilePrintCommand();
                }
                else {
                    return new StreamPrintCommand();
                }
            }
            case "SQRT" -> {
                return new SqrtCommand();
            }
            case "DEFINE" -> {
                return new DefineCommand();
            }
            default -> {
                throw new ClassNotFoundException("No such class found");
            }
        }
    }
}
