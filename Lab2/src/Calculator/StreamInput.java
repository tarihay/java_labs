package Calculator;

import java.util.Scanner;

public class StreamInput {
    private static final String END_COMMAND = "END";
    private static final String DELIMETER = " ";

    public void parseStream(BaseContext context) {
        Scanner stream = new Scanner(System.in);
        while (stream.nextLine() != END_COMMAND) {
            String curStr = stream.nextLine();
            String[] words = curStr.split(DELIMETER);

        }
    }
}
