package calculator.tests;

import calculator.BaseContext;
import calculator.commands.SqrtCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class SqrtCommandTest {

    @Test
    void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {};
        Worker command = new SqrtCommand();

        context.push(9);

        command.execute(context, arguments);

        assertEquals(3, context.peek());
    }
}