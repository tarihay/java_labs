package ru.nsu.gorin.lab4.factory.suppliers;

import java.util.function.Supplier;

/**
 * Абстрактный класс-поставщик с задержкой
 * @param <T> может принимать любой тип
 */
public abstract class DelaySupplier<T> implements Supplier<T> {
    private volatile int delay;

    public DelaySupplier(int delay) {
        this.delay = delay;
    }

    public void setDelay(int newDelay) {
        delay = newDelay;
    }

    public int getDelay() {
        return delay;
    }
}
