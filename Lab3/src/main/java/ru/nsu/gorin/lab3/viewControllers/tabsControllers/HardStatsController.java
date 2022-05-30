package ru.nsu.gorin.lab3.viewControllers.tabsControllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ru.nsu.gorin.lab3.Constants.*;

/**
 * Класс-контроллер HardStatsTab.fxml файла
 */
public class HardStatsController {
    private static final Logger logger = LogManager.getLogger(HardStatsController.class);

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
    void initialize() throws IOException {
        fillThePlaces();
    }

    /**
     * Метод заполняет поля вкладки данными из файла easyStatsResults.txt
     */
    public void fillThePlaces() throws IOException {
        File fin = new File(HARD_RESULTS_PATH);
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fin));
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            logger.error(e);
            throw new IOException();
        }

        int i = 0;
        for (String iterator : list) {
            modifyRightLabel(i, iterator);
            i++;
            if (i > PLACES_AMOUNT) {
                break;
            }
        }
    }

    /**
     * Метод вставляет данные в нужный Text-Label
     * @param position позиция, на которой стоит конкретное значение
     * @param string данные для вставки
     */
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
