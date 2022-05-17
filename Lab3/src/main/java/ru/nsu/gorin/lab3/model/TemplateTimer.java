package ru.nsu.gorin.lab3.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.gorin.lab3.viewControllers.GamePrepWindowController;
import ru.nsu.gorin.lab3.viewControllers.GameWindowController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.nsu.gorin.lab3.Constants.SEC_IN_MILLIS;

/**
 * Класс таймера для игры
 * Создается в контроллере окна подготовки к игре отдельным потоком
 * Необходим в окне игры
 *
 * @see GamePrepWindowController
 * @see GameWindowController
 */
public class TemplateTimer {
    private static final Logger logger = LogManager.getLogger(TemplateTimer.class);

    private static final int FIRST_TWO_DIGIT_NUM = 10;
    private static final int SECONDS_BORDER = 59;
    private static final String TIMER_DELIMITER = ":";

    private Integer seconds = 0;
    private Integer minutes = 0;
    private String result = "";

    private boolean workingStatus = true;

    private final ExecutorService service = Executors.newCachedThreadPool();
    private List<TimerListener> listenersList= new ArrayList<>();

    /**
     * Конструктор запускает сервис с таймером
     */
    public TemplateTimer() {
        logger.info("Starting ExecutorService");
        service.submit(new Runnable() {
            @Override
            public void run() {

                while (workingStatus) {
                    try {
                        Thread.sleep(SEC_IN_MILLIS);
                    }
                    catch (Exception e)  {
                        logger.error(e);
                    }

                    changeValues();
                    for (TimerListener listener : listenersList) {
                        listener.onReadingChange();
                    }
                }
            }
        });
    }

    public void addListener(TimerListener listener) {
        listenersList.add(listener);
    }

    public String getResult() {
        return result;
    }

    /**
     * Метод завершает работу таймера
     */
    public void shutdown() {
        logger.info("Shutdown requested");
        workingStatus = false;
        service.shutdown();
    }

    /**
     * Метод увеличивает секунды и преобразует их во время формата MM:SS
     */
    private void changeValues() {
        if (seconds + 1 > SECONDS_BORDER) {
            minutes++;
            seconds = 0;
        }
        else {
            seconds++;
        }

        String secondsString = seconds.toString();
        if (seconds < FIRST_TWO_DIGIT_NUM) {
            secondsString = "0" + secondsString;
        }
        String minutesString = minutes.toString();
        if (minutes < FIRST_TWO_DIGIT_NUM) {
            minutesString = "0" + minutesString;
        }

        result = minutesString + TIMER_DELIMITER + secondsString;
    }
}