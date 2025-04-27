import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"hanie",24));
        employeeList.add(new Employee(2,"hossein",25));
        employeeList.add(new Employee(3,"John",34));
        employeeList.add(new Employee(4,"Eric",27));
        employeeList.add(new Employee(5,"Alice",24));
        employee.write(employeeList);
        employee.read();
    }
}