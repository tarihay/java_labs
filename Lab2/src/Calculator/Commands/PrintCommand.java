package Calculator.Commands;

import Calculator.BaseContext;

public class PrintCommand implements Worker{
    @Override
    public void execute(BaseContext context, String[] arguments) {
        System.out.println(context.peek());
    }
}
