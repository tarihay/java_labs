package ru.nsu.gorin.lab3.model;

/**
 * Лиснер таймера
 * Экземпляр имплентируется в контроллере окна подготовки к игре
 * @see ru.nsu.gorin.lab3.controllers.GamePrepWindowController
 */
public interface TimerListener {
    void onReadingChange();
}
