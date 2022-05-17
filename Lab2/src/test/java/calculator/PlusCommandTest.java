package calculator;

import ru.nsu.gorin.lab2.calculator.BaseContext;
import ru.nsu.gorin.lab2.calculator.commands.PlusCommand;
import ru.nsu.gorin.lab2.calculator.commands.Worker;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static ru.nsu.gorin.lab2.calculator.Constants.STREAM_METHOD;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit38ClassRunner.class)
class PlusCommandTest {

    @Test
    public void execute() throws Exception {
        BaseContext context = new BaseContext(STREAM_METHOD);
        String[] arguments = {};
        Worker command = new PlusCommand();

        context.push(10);
        context.push(2);

        command.execute(context, arguments);

        assertEquals(12, context.peek());
    }
}