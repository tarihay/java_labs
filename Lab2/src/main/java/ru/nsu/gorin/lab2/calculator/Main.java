package ru.nsu.gorin.lab2.calculator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ru.nsu.gorin.lab2.calculator.Constants.FILE_METHOD;
import static ru.nsu.gorin.lab2.calculator.Constants.STREAM_METHOD;
import static ru.nsu.gorin.lab2.calculator.Constants.INPUT_ARGUMENT;
import static ru.nsu.gorin.lab2.calculator.Constants.OUTPUT_ARGUMENT;

public class Main {
    private static final int NO_ARGS = 0;
    private static final int IN_OUT_ARGS = 2;

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        if (args.length == NO_ARGS) {
            logger.info("There are no args. Program starts with console input");
            BaseContext context = new BaseContext(STREAM_METHOD);
            StreamInput.parseStream(context);
        }
        else if (args.length == IN_OUT_ARGS) {
            logger.info("There are 2 args. Program starts with file input");
            BaseContext context = new BaseContext(args[INPUT_ARGUMENT], args[OUTPUT_ARGUMENT], FILE_METHOD);
            FileTreatment.parseFile(context);
        }
        else {
            logger.error("Wrong amount of arguments given");
        }
    }
}
