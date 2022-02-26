package Calculator;

import Calculator.Commands.Worker;
import Calculator.Exceptions.*;

public class Main {
    private static final int FILE = 0;
    private static final int STREAM = 1;

    public static void main(String[] args) throws Exception {
        BaseContext context = new BaseContext();
        //TODO: Вставить логику парсинга строки

        Worker factory = Fabric.createFactory("POP");
        factory.execute(context, args);

        final int inputMethod;
        if (args.length == 0) {
            StreamInput input;
        }
        else if (args.length == 1) {
            inputMethod = FILE;
        }
        else {
            throw new ArgsAmountException("Wrong amount of arguments. Check command line");
        }
    }
}
