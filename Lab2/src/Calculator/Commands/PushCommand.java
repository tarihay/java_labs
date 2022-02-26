package Calculator.Commands;

import Calculator.BaseContext;

public class PushCommand implements Worker{
    @Override
    public void execute(BaseContext context, String[] arguments) {
        double num = 0.0;
        try {
            num = Double.parseDouble(arguments[0]);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        context.push(num);
    }
}
