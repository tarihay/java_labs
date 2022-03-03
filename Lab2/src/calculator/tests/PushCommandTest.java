package calculator.tests;

import calculator.BaseContext;
import calculator.commands.PushCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class PushCommandTest {

    @Test
    void execute() throws Exception {
        Worker command = new PushCommand();
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {"5"};

        command.execute(context, arguments);
        assertEquals(5, context.peek());

        context.define("a", 9);
        arguments = new String[]{"a"};

        command.execute(context, arguments);
        assertEquals(9, context.peek());
    }
}