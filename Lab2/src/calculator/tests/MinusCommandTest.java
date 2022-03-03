package calculator.tests;

import calculator.BaseContext;
import calculator.commands.MinusCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class MinusCommandTest {

    @Test
    void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {};
        Worker command = new MinusCommand();

        context.push(10);
        context.push(5);

        command.execute(context, arguments);

        assertEquals(5, context.peek());
    }
}