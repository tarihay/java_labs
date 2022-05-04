package ru.nsu.gorin.lab4.factory;

import ru.nsu.gorin.lab4.factory.gui.FactoryGUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FactoryGUI.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
