package Calculator;

import Calculator.Commands.Worker;
import Calculator.Exceptions.*;

public class Main {
    private static final int FILE = 0;
    private static final int STREAM = 1;

    public static void main(String[] args) throws Exception {
        BaseContext context = new BaseContext();

        final int inputMethod;
        if (args.length == 0) {
            StreamInput input = new StreamInput();
            input.parseStream(context);
        }
        else if (args.length == 1) {
            inputMethod = FILE;
        }
        else {
            throw new ArgsAmountException("Wrong amount of arguments. Check command line");
        }
    }
}
