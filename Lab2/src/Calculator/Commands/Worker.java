package Calculator.Commands;

import Calculator.BaseContext;

public interface Worker {
    void execute(BaseContext context, String[] arguments);
}
