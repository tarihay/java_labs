package ru.nsu.gorin.lab3.controllers.tabsControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

import static ru.nsu.gorin.lab3.Constants.*;

public class HardStatsController {

    @FXML
    private Text firstPlaceText;
    @FXML
    private Text secondPlaceText;
    @FXML
    private Text thirdPlaceText;
    @FXML
    private Text fourthPlaceText;
    @FXML
    private Text fifthPlaceText;
    @FXML
    private Text sixthPlaceText;
    @FXML
    private Text seventhPlaceText;

    @FXML
    void initialize() {
        fillThePlaces();
    }

    public void fillThePlaces() {
        File fin = new File(HARD_RESULTS_PATH);
        Map<Integer, String> stringValueMap = new TreeMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fin));
            String line = bufferedReader.readLine();
            while (line != null) {
                int spacePos = getSpacePos(line);
                String time = line.substring(0, spacePos);
                int timeValue = convertStringToSecs(time);

                stringValueMap.put(timeValue, line);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (Map.Entry<Integer, String> it : stringValueMap.entrySet()) {
            modifyRightLabel(i, it.getValue());
            i++;
        }
    }

    private int convertStringToSecs(String stringTime) {
        int colonPos = NOT_OK_STATUS;
        for (int i = 0; i < stringTime.length(); i++) {
            if (stringTime.charAt(i) == TIME_DELIMITER) {
                colonPos = i;
            }
        }

        String minutesString;
        String secondsString;
        if (colonPos != NOT_OK_STATUS) {
            minutesString = stringTime.substring(0, colonPos);
            secondsString = stringTime.substring(colonPos + 1, stringTime.length() - 1);
        }
        else {
            return NOT_OK_STATUS;
        }

        int minutes = 0;
        int seconds = 0;
        try {
            minutes = Integer.parseInt(minutesString);
            seconds = Integer.parseInt(secondsString);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return (minutes*SECONDS_IN_MINUTE + seconds);
    }

    private int getSpacePos(String string) {
        int spacePos = NOT_OK_STATUS;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == SPACE) {
                spacePos = i;
                break;
            }
        }

        return spacePos;
    }

    private void modifyRightLabel(int position, String string) {
        if (position == 0) {
            firstPlaceText.setText(string);
        }
        if (position == 1) {
            secondPlaceText.setText(string);
        }
        if (position == 2) {
            thirdPlaceText.setText(string);
        }
        if (position == 3) {
            fourthPlaceText.setText(string);
        }
        if (position == 4) {
            fifthPlaceText.setText(string);
        }
        if (position == 5) {
            sixthPlaceText.setText(string);
        }
        if (position == 6) {
            seventhPlaceText.setText(string);
        }
    }
}
