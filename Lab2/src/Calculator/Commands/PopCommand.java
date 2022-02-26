package Calculator.Commands;

import Calculator.BaseContext;

import java.util.EmptyStackException;

public class PopCommand implements Worker {
    @Override
    public void execute(BaseContext context, String[] arguments) {
        try {
            context.popDelete();
        }
        catch (EmptyStackException ex) {
            ex.printStackTrace();
        }
    }
}
