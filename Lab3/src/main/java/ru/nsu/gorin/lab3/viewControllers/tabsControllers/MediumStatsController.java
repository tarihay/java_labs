package ru.nsu.gorin.lab3.viewControllers.tabsControllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

import static ru.nsu.gorin.lab3.Constants.*;

/**
 * Класс-контроллер MediumStatsTab.fxml файла
 */
public class MediumStatsController {
    private static final Logger logger = LogManager.getLogger(MediumStatsController.class);

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

    private final Text[] places = {firstPlaceText, secondPlaceText, thirdPlaceText, fourthPlaceText, fifthPlaceText,
            sixthPlaceText, seventhPlaceText};

    @FXML
    void initialize() throws IOException {
        fillThePlaces();
    }

    /**
     * Метод заполняет поля вкладки данными из файла easyStatsResults.txt
     */
    public void fillThePlaces() throws IOException {
        File fin = new File(MEDIUM_RESULTS_PATH);
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
        places[position].setText(string);
    }
}
