package ru.nsu.gorin.lab4.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.gui.FactoryGUI;
import ru.nsu.gorin.lab4.factory.suppliers.BodySupplier;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        try {
            FactoryGUI.start();
        } catch (IOException e) {
            logger.error(e);
            throw e;
        }
    }
}
