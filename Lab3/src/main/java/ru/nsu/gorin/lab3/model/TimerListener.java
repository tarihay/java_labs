package ru.nsu.gorin.lab3.model;

import ru.nsu.gorin.lab3.viewControllers.GamePrepWindowController;

/**
 * Лиснер таймера
 * Экземпляр имплентируется в контроллере окна подготовки к игре
 * @see GamePrepWindowController
 */
public interface TimerListener {
    void onReadingChange();
}
