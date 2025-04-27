import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Employee {

    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return  id + " - " + name + " - " + age ;
    }

    public Employee setAge(int age) {
        this.age = age;
        return this;
    }
    Employee(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    Employee(){

    }
    private File file = new File("emplyees.csv");
    public void read(){
        if(this.file.exists()){
        try(BufferedReader reader = new BufferedReader(new FileReader(this.file))){
           String line;
           while ((line = reader.readLine()) != null){
               String[] values = line.split(",");
               System.out.println(Arrays.toString(values));
           }
        } catch (IOException e) {
            System.out.println("Error in Reading.");
            e.printStackTrace();
        }
        }else {
            System.out.println("there's no such file! first try to create it!");
        }
    }
    public void write(List<Employee> employees){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("emplyees.csv"))) {
                writer.write("id,name,age");
                writer.newLine();
                for (Employee employee:employees) {
                    writer.write(employee.getId() + "," + employee.getName() + "," + employee.getAge());
                    writer.newLine();
                }

            } catch (IOException e) {
                System.out.println("Error in Writing.(1)");
                e.printStackTrace();
            }
    }
}
