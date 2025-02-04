package Controllers;

import BLL.DepartmentsService;
import DAL.DepartmentsDAL;
import Views.DepartmentsView;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Program {

    public static void main(String[] args) {
        DepartmentsService service = new DepartmentsService();
        DepartmentsDAL dal = new DepartmentsDAL();
        DepartmentsView view = new DepartmentsView();

        DepartmentsService.setMessagePrinter(view::PrintMessage);

        Map<String, String> arguments = parseArguments(args);
        String inputFilePath = arguments.get("--input");
        String outputFilePath = arguments.get("--path");

        if (inputFilePath == null) {
            view.PrintMessage("Ошибка: Не указан путь к входному файлу. Используйте флаг --input=<path>");
            return;
        }

        try {
            List<String> lines = dal.readData(inputFilePath);
            service.processInputData(lines);

            String sortBy = arguments.getOrDefault("--sort", "none");
            String order = arguments.getOrDefault("--order", "asc");
            service.applySorting(sortBy, order);

            if (arguments.getOrDefault("--output", "console").equalsIgnoreCase("file")) {
                if (outputFilePath == null) {
                    view.PrintMessage("Ошибка: Не указан путь для вывода файла. Используйте флаг --path=<path>");
                    return;
                }
                dal.writeDataToFile(service.getManagers(), service.getInvalidData(), outputFilePath);
            } else {
                view.printOutputToConsole(service.getManagers(), service.getInvalidData());
            }
        } catch (IOException e) {
            view.PrintMessage("Ошибка чтения файла: " + e.getMessage());
        }
    }
    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] parts = arg.split("=", 2);
                arguments.put(parts[0], parts[1]);
            }
        }
        return arguments;
    }
}
