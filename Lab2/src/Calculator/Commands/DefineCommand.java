package Calculator.Commands;

import Calculator.BaseContext;

public class DefineCommand implements Worker {
    @Override
    public void execute(BaseContext context, String[] arguments) {
        double num = 0.0;
        try {
            num = Double.parseDouble(arguments[1]);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        context.define(arguments[0], num);
    }
}
