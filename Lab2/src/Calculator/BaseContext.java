package Calculator;

import java.util.HashMap;
import java.util.Stack;

public class BaseContext {
    private Stack<Double> stack;
    private HashMap<String, Double> defines;

    public BaseContext() {
        stack = new Stack<>();
        defines = new HashMap<>();
    }

    public void push(double num) {
        stack.push(num);
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
