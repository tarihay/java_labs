package calculator;

import java.util.HashMap;
import java.util.Stack;

/**
 * Класс контекст, содержащий в себе стэк калькулятора, мапу и все методы для работы с ними
 */
public class BaseContext {
    private Stack<Double> stack;
    private HashMap<String, Double> defines;
    String finName;
    String foutName;
    String inputMethod;

    /**
     * Конструктор класса, предназначенный для работы с потоком с консоли
     * @param inputMethod указывает метод ввода данных, для выбора реализации обработки данных и вывода
     */
    public BaseContext(String inputMethod) {
        this.inputMethod = inputMethod;
        stack = new Stack<>();
        defines = new HashMap<>();
    }

    /**
     * Конструктор класса, предназначенный для работы с потоком с файла
     * @param finName Имя файла, с которого нужно будет считывать данные
     * @param foutName Имя файла, в который нужно будет записывать результат
     * @param inputMethod указывает метод ввода данных, для выбора реализации обработки данных и вывода
     */
    public BaseContext(String finName, String foutName, String inputMethod) {
        this.inputMethod = inputMethod;
        this.foutName = foutName;
        this.finName = finName;
        stack = new Stack<>();
        defines = new HashMap<>();
    }

    public String getInputMethod() {
        return inputMethod;
    }

    public String getFinName() {
        return finName;
    }

    public String getFoutName() {
        return foutName;
    }

    public void push(double num) {
        stack.push(num);
    }

    public void push(String arg) {
        if (defines.containsKey(arg)) {
            stack.push(defines.get(arg));
        }
    }

    public void popDelete() {
        stack.pop();
    }

    public double popReturn() {
        return stack.pop();
    }

    public double peek() {
        return stack.peek();
    }

    public Stack<Double> getStack() {
        return stack;
    }

    public HashMap<String, Double> getDefines() {
        return defines;
    }

    public void define(String key, double value) {
        defines.put(key, value);
    }

    public double getDefine(String name) {
        return defines.get(name);
    }

    public boolean containsArg(String name) {
        return defines.containsKey(name);
    }
}
