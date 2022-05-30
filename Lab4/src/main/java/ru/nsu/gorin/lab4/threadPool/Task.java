package ru.nsu.gorin.lab4.threadPool;

/**
 * Интерфейс задачи
 */
public interface Task {
    String getName();

    void performWork() throws InterruptedException;
}
