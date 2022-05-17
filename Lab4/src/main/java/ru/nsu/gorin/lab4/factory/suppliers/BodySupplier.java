package ru.nsu.gorin.lab4.factory.suppliers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.parts.Body;

/**
 * Класс-поставщик кузова
 * Расширяет абстрактный класс поставщика с задержкой
 * @see DelaySupplier
 */
public class BodySupplier extends DelaySupplier<Body> {
    private static final Logger logger = LogManager.getLogger(BodySupplier.class);

    public BodySupplier(int delay) {
        super(delay);
    }

    public Body get() {
        try {
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            logger.info("Caught InterruptedException, returning null");
            return null;
        }
        return new Body();
    }
}
