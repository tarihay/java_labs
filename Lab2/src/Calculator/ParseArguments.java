package Calculator;

import Calculator.Commands.Worker;

import java.util.Objects;

public class ParseArguments {
    private static final int FIRST_ARG = 1;
    private static final int COMMAND_POS = 0;
    private static final int ARRAY_START = 0;
    private static final int NO_ARGS = 0;

    private static final char COMMENT = '#';

    public static void parseArgs(String[] arguments, BaseContext context) throws Exception{
        final int LENGTH_WITHOUT_COMMAND = arguments.length - 1;
        String command = arguments[COMMAND_POS];
        String[] additions = new String[LENGTH_WITHOUT_COMMAND];
        if (LENGTH_WITHOUT_COMMAND > NO_ARGS) {
            System.arraycopy(arguments, FIRST_ARG, additions, ARRAY_START, LENGTH_WITHOUT_COMMAND);
        }

        if (!Objects.equals(command.charAt(COMMAND_POS), COMMENT)) {
            Worker factory = Factory.createFactory(command);
            factory.execute(context, additions);
        }
    }
}
