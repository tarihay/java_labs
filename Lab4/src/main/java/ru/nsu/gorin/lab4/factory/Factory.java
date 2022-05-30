package ru.nsu.gorin.lab4.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab4.factory.controllers.BuildController;
import ru.nsu.gorin.lab4.factory.controllers.DealController;
import ru.nsu.gorin.lab4.factory.controllers.PriceController;
import ru.nsu.gorin.lab4.factory.parts.Accessory;
import ru.nsu.gorin.lab4.factory.parts.Body;
import ru.nsu.gorin.lab4.factory.parts.Car;
import ru.nsu.gorin.lab4.factory.parts.Engine;
import ru.nsu.gorin.lab4.factory.store.Dealer;
import ru.nsu.gorin.lab4.factory.store.Store;
import ru.nsu.gorin.lab4.factory.suppliers.AccessorySupplier;
import ru.nsu.gorin.lab4.factory.suppliers.BodySupplier;
import ru.nsu.gorin.lab4.factory.suppliers.EngineSupplier;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * Класс фабрики машин
 */
public class Factory {
    private final static Logger logger = LogManager.getLogger(Factory.class);

    private final static String propertiesFilename = "/factory.properties";

    private final BodySupplier carBodySupplier;
    private final EngineSupplier carEngineSupplier;
    private final AccessorySupplier carAccessorySupplier;

    private final Store<Body> carBodyStore;
    private final Store<Engine> carEngineStore;
    private final Store<Accessory> carAccessoryStore;
    private final Store<Car> carStore;

    private final ProductionThread<Body> carBodyProductionThread;
    private final ProductionThread<Engine> carEngineProductionThread;
    private final ProductionThread<Accessory> carAccessoryProductionThread;

    private final BuildController carBuildController;
    private final DealController carDealController;
    private final PriceController carPriceController;

    private final Dealer dealer;


    /**
     * Метод-конструктор фабрики
     * Устанавливает все настройки, заданные в файле factory.properties
     * @throws IOException
     */
    public Factory() throws IOException {
        var properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(propertiesFilename));
        } catch (IOException e) {
            logger.error("Can't load " + propertiesFilename);
            throw e;
        }

        carStore = new Store<>(Integer.parseInt((String) properties.get("CAR.STORE_SIZE")));

        carBodySupplier = new BodySupplier(Integer.parseInt((String) properties.get("CAR_BODY.DELAY")));
        carBodyStore = new Store<>(Integer.parseInt((String) properties.get("CAR_BODY.STORE_SIZE")));
        carBodyProductionThread = new ProductionThread<>(carBodySupplier, carBodyStore);

        carEngineSupplier = new EngineSupplier(Integer.parseInt((String) properties.get("CAR_ENGINE.DELAY")));
        carEngineStore = new Store<>(Integer.parseInt((String) properties.get("CAR_ENGINE.STORE_SIZE")));
        carEngineProductionThread = new ProductionThread<>(carEngineSupplier, carEngineStore);

        carAccessorySupplier = new AccessorySupplier(Integer.parseInt((String) properties.get("CAR_ACCESSORY.DELAY")));
        carAccessoryStore = new Store<>(Integer.parseInt((String) properties.get("CAR_ACCESSORY.STORE_SIZE")));
        carAccessoryProductionThread = new ProductionThread<>(carAccessorySupplier, carAccessoryStore);

        dealer = new Dealer();
        int numOfDealers = Integer.parseInt((String) properties.get("DEALER.NUM"));

        int numOfBuilders = Integer.parseInt((String) properties.get("WORKER.NUM"));

        logger.info("Stores, suppliers and threads created");

        carBuildController = new BuildController(carBodyStore, carEngineStore, carAccessoryStore, carStore, numOfBuilders);
        carPriceController = new PriceController(dealer, new PriceController.FactoryProductionControlAdapter() {
            @Override
            public boolean isPaused() {
                return carBuildController.isPaused();
            }

            @Override
            public void pauseProduction() {
                carBuildController.pauseProduction();
            }

            @Override
            public void continueProduction() {
                carBuildController.continueProduction();
            }
        });
        carDealController = new DealController(carStore, dealer, numOfDealers);

        logger.info("Controllers created");
    }

    /**
     * Метод запускает фабрику
     */
    public void start() {
        carBodyProductionThread.start();
        carEngineProductionThread.start();
        carAccessoryProductionThread.start();

        logger.info("Production threads started");

        carBuildController.start();
        carDealController.start();

        carPriceController.start();

        logger.info("Controllers started");
    }

    /**
     * Метод закрывает работу фабрики
     */
    public void shutdown() {
        logger.info("Shutdown issued");

        carBodyProductionThread.shutdown();
        carEngineProductionThread.shutdown();
        carAccessoryProductionThread.shutdown();

        carBuildController.shutdown();
        carDealController.shutdown();
    }

    public int getCarBodyStoreSize() {
        return carBodyStore.getSize();
    }

    public int getCarBodyStoreCapacity() {
        return carBodyStore.getCapacity();
    }

    public int getCarBodySupplierDelay() {
        return carBodySupplier.getDelay();
    }

    public void setCarBodySupplierDelay(int delay) {
        carBodySupplier.setDelay(delay);
    }

    public int getCarEngineStoreSize() {
        return carEngineStore.getSize();
    }

    public int getCarEngineStoreCapacity() {
        return carEngineStore.getCapacity();
    }

    public int getCarEngineSupplierDelay() {
        return carEngineSupplier.getDelay();
    }

    public void setCarEngineSupplierDelay(int delay) {
        carEngineSupplier.setDelay(delay);
    }

    public int getCarAccessoryStoreSize() {
        return carAccessoryStore.getSize();
    }

    public int getCarAccessoryStoreCapacity() {
        return carAccessoryStore.getCapacity();
    }

    public int getCarAccessorySupplierDelay() {
        return carAccessorySupplier.getDelay();
    }

    public void setCarAccessorySupplierDelay(int delay) {
        carAccessorySupplier.setDelay(delay);
    }

    public BigDecimal getDealerCarPrice() {
        return dealer.getCarPrice();
    }

    public int getTotalSold() {
        return carDealController.getTotalSold();
    }

    public BigDecimal getTotalGain() {
        return carDealController.getTotalGain();
    }


    public boolean isBuildingPaused() {
        return carBuildController.isPaused();
    }
}
