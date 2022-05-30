package ru.nsu.gorin.lab4.factory.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.store.Dealer;

import java.math.BigDecimal;

/**
 * Класс-контроллер цены за одну машину
 */
public class PriceController extends Thread {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private static final int ONE_SEC = 1000;

    private final static int OK_CAR_PRICE = 500;

    private final Dealer dealer;
    private final FactoryProductionControlAdapter adapter;

    public PriceController(Dealer dealer, FactoryProductionControlAdapter adapter) {
        this.dealer = dealer;
        this.adapter = adapter;
        this.setDaemon(true);
    }

    /**
     * Метод контролирует работу конвейера в зависимости от цены
     * Если цена не удовлетворяет условию, конвейер приостанавливается
     */
    @Override
    public void run() {
        while (true) {
            var carPrice = dealer.getCarPrice();
            if (isCarPriceOk(carPrice)) {
                logger.info(carPrice);
                if (adapter.isPaused()) {
                    logger.info("Car price is ok: " + carPrice + "; starting production back...");
                    adapter.continueProduction();
                }
            } else if (!adapter.isPaused()) {
                logger.info("Car price is NOT ok: " + carPrice + "; stopping production...");
                adapter.pauseProduction();
            }

            try {
                Thread.sleep(ONE_SEC);
            } catch (InterruptedException e) {
                logger.warn(e);
                break;
            }
        }
    }

    private boolean isCarPriceOk(BigDecimal carPrice) {
        return carPrice.compareTo(BigDecimal.valueOf(OK_CAR_PRICE)) >= 0;
    }

    public interface FactoryProductionControlAdapter {
        boolean isPaused();

        void pauseProduction();

        void continueProduction();
    }
}
