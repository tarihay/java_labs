package calculator.tests;

import calculator.BaseContext;
import calculator.commands.PlusCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class PlusCommandTest {

    @Test
    void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {};
        Worker command = new PlusCommand();

        context.push(10);
        context.push(2);

        command.execute(context, arguments);

        assertEquals(12, context.peek());
    }
}