package calculator.tests;

import calculator.BaseContext;
import calculator.commands.MultCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class MultCommandTest {

    @Test
    void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {};
        Worker command = new MultCommand();

        context.push(5);
        context.push(2);

        command.execute(context, arguments);

        assertEquals(10, context.peek());
    }
}