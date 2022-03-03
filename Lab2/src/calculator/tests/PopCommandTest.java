package calculator.tests;

import calculator.BaseContext;
import calculator.commands.PopCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class PopCommandTest {

    @Test
    void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {};
        Worker command = new PopCommand();

        context.push(5);
        context.push(2);

        command.execute(context, arguments);

        assertEquals(5, context.peek());
    }
}