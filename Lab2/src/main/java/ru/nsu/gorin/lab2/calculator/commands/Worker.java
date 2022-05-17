package ru.nsu.gorin.lab2.calculator.commands;

import ru.nsu.gorin.lab2.calculator.BaseContext;

/**
 * Интерфейс, от которого имплементируются блоки
 */
public interface Worker {
    /**
     * Прототип функции execute каждого из блоков
     * @param context объект класса, содержащий стэк, мапу и методы для работы с ними
     * @param arguments аргументы, передающиеся для команды
     */
    void execute(BaseContext context, String[] arguments) throws Exception;
}
