package calculator;

import calculator.exceptions.*;

import static calculator.Constants.FILE_METHOD;
import static calculator.Constants.STREAM_METHOD;
import static calculator.Constants.INPUT_ARGUMENT;
import static calculator.Constants.OUTPUT_ARGUMENT;

public class Main {
    private static final int NO_ARGS = 0;
    private static final int IN_OUT_ARGS = 2;

    public static void main(String[] args) throws Exception {
        if (args.length == NO_ARGS) {
            BaseContext context = new BaseContext(STREAM_METHOD);
            StreamInput.parseStream(context);
        }
        else if (args.length == IN_OUT_ARGS) {
            BaseContext context = new BaseContext(args[INPUT_ARGUMENT], args[OUTPUT_ARGUMENT], FILE_METHOD);
            FileTreatment.parseFile(context);
        }
        else {
            throw new ArgsAmountException("Wrong amount of arguments. Check command line");
        }
    }
}
