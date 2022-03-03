package calculator.tests;

import calculator.BaseContext;
import calculator.commands.DefineCommand;
import calculator.commands.Worker;
import org.junit.jupiter.api.Test;

import static calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

class DefineCommandTest {

    @Test
    void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {"a", "5"};
        Worker command = new DefineCommand();

        command.execute(context, arguments);
        context.push("a");

        assertEquals(5, context.peek());
    }
}