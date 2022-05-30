package ru.nsu.gorin.lab4.factory.suppliers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.parts.Accessory;

/**
 * Класс-поставщик аксессуаров
 * Расширяет абстрактный класс поставщика с задержкой
 * @see DelaySupplier
 */
public class AccessorySupplier extends DelaySupplier<Accessory> {
    private static final Logger logger = LogManager.getLogger(AccessorySupplier.class);

    public AccessorySupplier(int delay) {
        super(delay);
    }

    public Accessory get() {
        try {
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            logger.info("Caught InterruptedException, returning null");
            return null;
        }
        return new Accessory();
    }
}
