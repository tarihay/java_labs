package ru.nsu.gorin.lab3.model;

import ru.nsu.gorin.lab3.controller.GameController;

public class Timer implements Runnable {
    private static final int SEC_IN_MILLIS = 1000;
    private static final int FIRST_TWO_DIGIT_NUM = 10;
    private static final int SECONDS_BORDER = 59;
    private static final int MINUTES_BORDER = 59;
    private static final String TIMER_DELIMITER = ":";

    private Integer seconds;
    private Integer minutes;
    private Integer hours;
    private String result;

    private boolean workingStatus;

    public Timer() {
        seconds = 0;
        minutes = 0;
        hours = 0;

        result = "";

        workingStatus = true;
    }

    public void setWorkingStatus(boolean status) {
        workingStatus = status;
    }

    @Override
    public void run() {
        while (workingStatus) {
            changeValues();

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void changeValues() {
        if (seconds + 1 > SECONDS_BORDER) {
            if (minutes + 1 > MINUTES_BORDER) {
                hours++;
                minutes = 0;
            }
            else {
                minutes++;
            }
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

        if (hours != 0) {
            result = hours.toString() + TIMER_DELIMITER + minutesString + TIMER_DELIMITER + secondsString;
        }
        else {
            result = minutesString + TIMER_DELIMITER + secondsString;
        }
    }
}