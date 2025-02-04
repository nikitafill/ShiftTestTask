package DAL;

import Beans.Manager;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class DepartmentsDAL {

    public List<String> readData(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public void writeDataToFile(List<Manager> managers, List<String> invalidData, String outputPath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath))) {
            for (Manager manager : managers) {
                writer.print(manager);
            }
            if (!invalidData.isEmpty()) {
                writer.println("Некорректные данные:");
                invalidData.forEach(writer::println);
            }
        }
    }
}
