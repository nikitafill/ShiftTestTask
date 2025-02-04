package Views;

import Beans.Manager;

import java.util.Comparator;
import java.util.List;

public class DepartmentsView {
    public void printOutputToConsole(List<Manager> managers, List<String> invalidData) {
        managers.sort(Comparator.comparing(Manager::getDepartmentName));
        for (Manager manager : managers) {
            System.out.println(manager);
        }
        if (!invalidData.isEmpty()) {
            System.out.println("Некорректные данные:");
            invalidData.forEach(System.out::println);
        }
    }

    public void PrintMessage(String message) {
        System.out.println(message);
    }
}
