package calculator;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.commands.DefineCommand;
import ru.nsu.gorin.lab2.calculator.commands.Worker;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static ru.nsu.gorin.lab2.calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit38ClassRunner.class)
class DefineCommandTest {

    @Test
    public void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {"a", "5"};
        Worker command = new DefineCommand();

        command.execute(context, arguments);
        context.push("a");

        assertEquals(5, context.peek());
    }
}