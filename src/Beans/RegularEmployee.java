package Beans;

public class RegularEmployee extends Employee {
    private int managerId;

    public RegularEmployee(String position, int id, String name, double salary, int managerId) {
        super(position, id, name, salary);
        this.managerId = managerId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
