// 檔案一：Employee.java
public class Employee {
    private int id;
    private String name;
    private String department;
    private String extension;

    public Employee(int id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "員工編號: " + id + ", 姓名: " + name + ", 部門: " + department + ", 分機: " + extension;
    }
}