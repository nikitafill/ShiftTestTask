package Beans;

import java.util.Locale;

public abstract class Employee {
    private String position;
    private int id;
    private String name;
    private double salary;

    public Employee(String position, int id, String name, double salary) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s, %d, %s, %.2f", position, id, name, salary);
    }
}
