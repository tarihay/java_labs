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

    public void define(String name, double num) {
        defines.put(name, num);
    }

    public double getDefine(String name) {
        return defines.get(name);
    }
}
