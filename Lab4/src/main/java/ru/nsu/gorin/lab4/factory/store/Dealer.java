package ru.nsu.gorin.lab4.factory.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.parts.Car;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

/**
 * Класс диллера
 */
public class Dealer {
    private static final Logger logger = LogManager.getLogger(Dealer.class);

    private static final int DEFAULT_CAR_PRICE = 500;
    private static final int DEFAULT_CAR_DELTA = 100;

    private static final int FIVE_SECONDS = 5000;

    private final Object monitor = new Object();

    private final Random random = new Random();
    private final BigDecimal delta = new BigDecimal(DEFAULT_CAR_DELTA);

    private BigDecimal carPrice = new BigDecimal(DEFAULT_CAR_PRICE);

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public Dealer() {
        var priceChangerThread = new Thread(this::priceChanger);
        priceChangerThread.setDaemon(true);
        priceChangerThread.start();
    }

    /**
     * Метод динамически меняет цену
     * Если цена упала больше, чем на заданную дефолтную дельта, цена снова становится дефолтной
     */
    private void priceChanger() {
        while (true) {
            double deltaK = random.nextGaussian();
            synchronized (monitor) {
                if (DEFAULT_CAR_DELTA + carPrice.intValue() < DEFAULT_CAR_PRICE) {
                    carPrice = new BigDecimal(DEFAULT_CAR_PRICE);
                }
                else {
                    var newPrice = carPrice.add(delta.multiply(BigDecimal.valueOf(deltaK)));
                    if (newPrice.compareTo(BigDecimal.ZERO) > 0) {
                        carPrice = carPrice.add(delta.multiply(BigDecimal.valueOf(deltaK)));
                    }
                }
            }
            try {
                Thread.sleep(FIVE_SECONDS);
            } catch (InterruptedException e) {
                logger.warn(e);
                break;
            }
        }
    }

    /**
     * метод "продает" машину
     * @param car конкретная машина под "продажу"
     * @return возвращает текущую цену машины
     */
    public BigDecimal sell(Car car) {
        //по необходимости можно использовать данные машины
        return carPrice;
    }
}
