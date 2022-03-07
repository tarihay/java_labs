package calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Класс обработки данных входного файла
 */
public class FileTreatment {
    private static final String DELIMETER = " ";

    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * Метод построчно считывает данные с файла и парсит их, разбивая на "команду" и ее аргументы
     * Вызывает метод parseArgs класса ParseArguments для разделения команды и аргументов (если имеются)
     * @param context Экземпляр класса BaseContext, в котором хранятся стэк, мапа и методы для работы с ними
     * @see calculator.BaseContext
     * @see calculator.ParseArguments
     */
    public static void parseFile(BaseContext context) throws Exception {
        File fin = new File(context.getFinName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fin));
            String curStr = reader.readLine();
            while (curStr != null) {
                String[] arguments = curStr.split(DELIMETER);

                ParseArguments.parseArgs(arguments, context);

                curStr = reader.readLine();
            }
        } catch (IOException ex) {
            logger.error(ex);
            ex.printStackTrace();
        }
    }
}
