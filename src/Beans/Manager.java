package Beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Manager extends Employee {
    private String departmentName;
    private List<Employee> employees = new ArrayList<>();

    public Manager(String position, int id, String name, double salary, String departmentName) {
        super(position, id, name, salary);
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getDepartmentCount() {
        return employees.size() + 1;
    }

    public double getAverageSalary() {
        return (employees.stream().mapToDouble(Employee::getSalary).sum() + getSalary()) / getDepartmentCount();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(departmentName + "\n");
        builder.append(super.toString()).append("\n");
        employees.forEach(e -> builder.append(e.toString()).append("\n"));
        builder.append(String.format(Locale.US, "%d, %.2f\n", getDepartmentCount(), getAverageSalary()));
        return builder.toString();
    }
}
