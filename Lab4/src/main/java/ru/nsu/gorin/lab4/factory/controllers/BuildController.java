package ru.nsu.gorin.lab4.factory.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.parts.Accessory;
import ru.nsu.gorin.lab4.factory.parts.Body;
import ru.nsu.gorin.lab4.factory.parts.Car;
import ru.nsu.gorin.lab4.factory.parts.Engine;
import ru.nsu.gorin.lab4.threadPool.Task;
import ru.nsu.gorin.lab4.threadPool.ThreadPool;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Класс контроллер построения машины
 */
public class BuildController extends Thread {
    private static final Logger logger = LogManager.getLogger(BuildController.class);

    private static final int QUEUE_SIZE = 1000;

    private final Supplier<Body> carBodyStore;
    private final Supplier<Engine> carEngineStore;
    private final Supplier<Accessory> carAccessoryStore;
    private final Consumer<Car> outputStore;
    private final ThreadPool threadPool;
    private final Object pauseObject = new Object();
    private volatile boolean isRunning = true;
    private volatile boolean pause = false;

    public BuildController(Supplier<Body> carBodyStore, Supplier<Engine> carEngineStore,
                              Supplier<Accessory> carAccessoryStore, Consumer<Car> outputStore, int numOfBuilders) {
        this.carBodyStore = carBodyStore;
        this.carEngineStore = carEngineStore;
        this.carAccessoryStore = carAccessoryStore;
        this.outputStore = outputStore;

        threadPool = new ThreadPool(numOfBuilders, QUEUE_SIZE);
    }

    /**
     * Проерка стоит конвейер или нет
     * @return Возвращает статус работы конвейера
     */
    public boolean isPaused() {
        return pause;
    }

    /**
     * Метод приостанавливает сборку машин
     */
    public void pauseProduction() {
        if (pause) throw new RuntimeException("double pause detected");
        pause = true;
    }

    /**
     * Метод возобновляет сборку машин
     */
    public void continueProduction() {
        if (!pause) throw new RuntimeException("double continue detected");
        pause = false;
        synchronized (pauseObject) {
            pauseObject.notifyAll();
        }
    }

    /**
     * Метоод полностью приостанавлиает работу тред-пула и сборку машин
     */
    public void shutdown() {
        isRunning = false;
        threadPool.shutdown();
    }

    /**
     * Основной метод сборки машины
     */
    @Override
    public void run() {
        while (isRunning) {
            var body = carBodyStore.get();
            var engine = carEngineStore.get();
            var accessory = carAccessoryStore.get();

            if (body == null || engine == null || accessory == null) {
                continue;
            }

            var task = new Task() {
                @Override
                public String getName() {
                    return "Build a car";
                }

                @Override
                public void performWork() {
                    var car = new Car(body, engine, accessory);
                    outputStore.accept(car);
                }
            };

            try {
                threadPool.addTask(task);
            } catch (InterruptedException e) {
                logger.error(e);
            }

            if (pause) {
                synchronized (pauseObject) {
                    try {
                        do {
                            pauseObject.wait();
                        } while (pause);
                    } catch (InterruptedException ignored) {
                        logger.info("Interrupted exception ignored");
                    }
                }
            }
        }
    }
}
