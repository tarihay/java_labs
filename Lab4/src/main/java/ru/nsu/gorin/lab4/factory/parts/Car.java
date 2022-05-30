package ru.nsu.gorin.lab4.factory.parts;

/**
 * Класс машины
 */
public class Car {
    private final Body body;
    private final Engine engine;
    private final Accessory accessory;

    public Car(Body body, Engine engine, Accessory accessory) {
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    public String toString() {
        return String.format("Car %d (Body: %d, Engine: %d, Accessory: %d)", this.hashCode(), this.body.hashCode(), this.engine.hashCode(), this.accessory.hashCode());
    }
}
