package ru.nsu.gorin.lab4.factory.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.parts.Car;
import ru.nsu.gorin.lab4.factory.store.Dealer;
import ru.nsu.gorin.lab4.threadPool.Task;
import ru.nsu.gorin.lab4.threadPool.ThreadPool;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Класс-контроллер диллера
 */
public class DealController extends Thread {
    private static final Logger logger = LogManager.getLogger(DealController.class);

    private static final int QUEUE_SIZE = 1000;

    private final Object monitor = new Object();

    private final Supplier<Car> outputStore;
    private final ThreadPool threadPool;
    private final AtomicInteger totalSold = new AtomicInteger(0);
    private BigDecimal totalGain = BigDecimal.ZERO;

    private final Dealer dealer;
    private volatile boolean isRunning = true;

    public DealController(Supplier<Car> outputStore, Dealer dealer, int numOfThreads) {
        this.dealer = dealer;
        this.outputStore = outputStore;

        threadPool = new ThreadPool(numOfThreads, QUEUE_SIZE);
    }

    /**
     * Метод показывает сколько машин за все время было продано
     * @return Возвращает количество проданных машин
     */
    public int getTotalSold() {
        return totalSold.intValue();
    }

    /**
     * Метод показывает общую прибыль за все время
     * @return Возвращает прибыль за все время
     */
    public BigDecimal getTotalGain() {
        synchronized (monitor) {
            return totalGain;
        }
    }

    /**
     * Метод высчитывает складывает прибыль
     * @param gain прибыль с продажи машины
     */
    private void addMoney(BigDecimal gain) {
        synchronized (monitor) {
            totalGain = totalGain.add(gain);
        }
    }

    /**
     * Метоод полностью приостанавлиает работу тред-пула и продажу машин
     */
    public void shutdown() {
        isRunning = false;
        threadPool.shutdown();
    }

    /**
     * Основной метод продажи машин
     */
    @Override
    public void run() {
        while (isRunning) {
            var car = outputStore.get();
            if (car == null) {
                continue;
            }

            var task = new Task() {
                @Override
                public String getName() {
                    return "Sell a car";
                }

                @Override
                public void performWork() {
                    totalSold.incrementAndGet();
                    addMoney(dealer.sell(car));
                    logger.info("Sold car to dealer " + Thread.currentThread().getName() +  ": " + car);
                }
            };
            try {
                threadPool.addTask(task);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}
