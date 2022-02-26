package Calculator.Commands;

import Calculator.BaseContext;

import java.util.EmptyStackException;

public class SqrtCommand implements Worker{
    @Override
    public void execute(BaseContext context, String[] arguments) {
        double num = 0.0;
        try {
            num = context.popReturn();
        }
        catch (EmptyStackException ex) {
            ex.printStackTrace();
        }

        num = Math.sqrt(num);
        context.push(num);
    }
}
