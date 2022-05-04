package ru.nsu.gorin.lab4.factory.suppliers;

import ru.nsu.gorin.lab4.factory.parts.Engine;

/**
 * Класс-поставщик двигателя
 * Расширяет абстрактный класс поставщика с задержкой
 * @see DelaySupplier
 */
public class EngineSupplier extends DelaySupplier<Engine> {
    public EngineSupplier(int delay) {
        super(delay);
    }

    public Engine get() {
        try {
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            return null;
        }
        return new Engine();
    }
}
