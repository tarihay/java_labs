package Calculator;

import Calculator.Commands.*;

/**
 * Класс реализации Фабрики команд калькулятора
 */
public class Factory {
    /**
     * Метод возвращает реализацию по указанной команде
     *
     * @param command - имя команды
     */
    public static Worker createFactory(String command) throws ClassNotFoundException {
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
                return new PrintCommand();
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
