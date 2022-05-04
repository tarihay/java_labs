package ru.nsu.gorin.lab4.factory.suppliers;

import ru.nsu.gorin.lab4.factory.parts.Body;

/**
 * Класс-поставщик кузова
 * Расширяет абстрактный класс поставщика с задержкой
 * @see DelaySupplier
 */
public class BodySupplier extends DelaySupplier<Body> {
    public BodySupplier(int delay) {
        super(delay);
    }

    public Body get() {
        try {
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            return null;
        }
        return new Body();
    }
}
