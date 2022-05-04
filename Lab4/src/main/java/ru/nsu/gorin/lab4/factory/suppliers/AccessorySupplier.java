package ru.nsu.gorin.lab4.factory.suppliers;

import ru.nsu.gorin.lab4.factory.parts.Accessory;

/**
 * Класс-поставщик аксессуаров
 * Расширяет абстрактный класс поставщика с задержкой
 * @see DelaySupplier
 */
public class AccessorySupplier extends DelaySupplier<Accessory> {
    public AccessorySupplier(int delay) {
        super(delay);
    }

    public Accessory get() {
        try {
            Thread.sleep(getDelay());
        } catch (InterruptedException e) {
            return null;
        }
        return new Accessory();
    }
}
