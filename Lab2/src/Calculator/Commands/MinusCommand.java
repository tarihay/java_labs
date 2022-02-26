package Calculator.Commands;

import Calculator.BaseContext;

import java.util.EmptyStackException;

public class MinusCommand implements Worker {
    @Override
    public void execute(BaseContext context, String[] arguments) {
        double num1 = 0.0;
        double num2 = 0.0;
        try {
            num1 = context.popReturn();
            num2 = context.popReturn();
        }
        catch (EmptyStackException ex) {
            ex.printStackTrace();
        }

        context.push(num2 - num1);
    }
}
