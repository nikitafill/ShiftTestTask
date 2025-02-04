package BLL;

import Beans.Employee;
import Beans.Manager;
import Beans.RegularEmployee;

import java.util.*;

public class DepartmentsService {
    private final List<Manager> managers = new ArrayList<>();
    private final List<String> invalidData = new ArrayList<>();

    private static MessagePrinter messagePrinter;

    public static void setMessagePrinter(MessagePrinter printer) {
        messagePrinter = printer;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public List<String> getInvalidData() {
        return invalidData;
    }

    public void processInputData(List<String> lines) {
        Map<Integer, Manager> managerMap = new HashMap<>();

        for (String line : lines) {
            String cleanedLine = line.trim();
            String[] parts = cleanedLine.split(",");

            if (parts.length < 5) {
                invalidData.add(cleanedLine);
                messagePrinter.printMessage("Ошибка: недостаточно данных в строке: " + cleanedLine);
                continue;
            }

            try {
                String position = parts[0].trim();
                int id = Integer.parseInt(parts[1].trim());
                String name = parts[2].trim();
                double salary = parseSalary(parts[3].trim());
                String departmentOrManagerId = parts[4].trim();

                if (position.equalsIgnoreCase("Manager")) {
                    Manager manager = new Manager(position, id, name, salary, departmentOrManagerId);
                    managers.add(manager);
                    managerMap.put(id, manager);
                } else if (position.equalsIgnoreCase("Employee")) {
                    int managerId = Integer.parseInt(departmentOrManagerId);
                    Manager manager = managerMap.get(managerId);
                    RegularEmployee employee = new RegularEmployee(position, id, name, salary, managerId);
                    if (manager != null) {
                        manager.getEmployees().add(employee);
                    } else {
                        invalidData.add(cleanedLine);
                        messagePrinter.printMessage("Ошибка: менеджер с ID " + managerId + " не найден для сотрудника: " + cleanedLine);
                    }
                } else {
                    invalidData.add(cleanedLine);
                    messagePrinter.printMessage("Ошибка: неверная позиция в строке: " + cleanedLine);
                }
            } catch (NumberFormatException e) {
                invalidData.add(cleanedLine);
                messagePrinter.printMessage("Ошибка: неверный формат числа в строке: " + cleanedLine);
            }
        }
    }

    private double parseSalary(String salaryStr) {
        if (salaryStr.isEmpty()) {
            return 0.0;
        }
        try {
            double salary = Double.parseDouble(salaryStr);
            if (salary < 0) throw new NumberFormatException();
            return salary;
        } catch (NumberFormatException e) {
            messagePrinter.printMessage("Ошибка: неверный формат зарплаты: " + salaryStr);
            throw new NumberFormatException();
        }
    }

    public void applySorting(String sortBy, String order) {
        for (Manager manager : managers) {
            if (sortBy.equalsIgnoreCase("name")) {
                manager.getEmployees().sort(Comparator.comparing(Employee::getName));
            } else if (sortBy.equalsIgnoreCase("salary")) {
                manager.getEmployees().sort(Comparator.comparing(Employee::getSalary));
            }

            if (order.equalsIgnoreCase("desc")) {
                Collections.reverse(manager.getEmployees());
            }
        }
    }
}


