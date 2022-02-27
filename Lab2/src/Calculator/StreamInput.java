package Calculator;

import java.util.Objects;
import java.util.Scanner;

public class StreamInput {
    private static final String END_COMMAND = "END";
    private static final String DELIMETER = " ";

    public void parseStream(BaseContext context) throws Exception {
        ParseArguments parser = new ParseArguments();

        Scanner stream = new Scanner(System.in);
        String curStr = stream.nextLine();
        while (!Objects.equals(curStr, END_COMMAND)) {
            String[] arguments = curStr.split(DELIMETER);

            parser.parseArgs(arguments, context);

            curStr = stream.nextLine();
        }
    }
}
