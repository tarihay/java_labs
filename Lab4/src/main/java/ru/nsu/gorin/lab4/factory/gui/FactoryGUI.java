package ru.nsu.gorin.lab4.factory.gui;

import java.io.IOException;

/**
 * Основной класс GUI фабрики
 */
public class FactoryGUI {
    public static void start() throws IOException {
        var window = new FactoryWindow();
        window.start();
    }
}
