package ru.nsu.gorin.lab3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.nsu.gorin.lab3.Constants.SEC_IN_MILLIS;

public class TemplateTimer {
    private static final int FIRST_TWO_DIGIT_NUM = 10;
    private static final int SECONDS_BORDER = 59;
    private static final String TIMER_DELIMITER = ":";

    private Integer seconds = 0;
    private Integer minutes = 0;
    private String result = "";

    private boolean workingStatus = true;

    private final ExecutorService service = Executors.newCachedThreadPool();
    private List<TimerListener> listenersList= new ArrayList<>();

    public TemplateTimer() {

        service.submit(new Runnable() {
            @Override
            public void run() {
                while (workingStatus) {
                    try {
                        Thread.sleep(SEC_IN_MILLIS);
                    }
                    catch (Exception exception)  {
                        exception.printStackTrace();
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

    public void shutdown() {
        workingStatus = false;
        service.shutdown();
    }

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