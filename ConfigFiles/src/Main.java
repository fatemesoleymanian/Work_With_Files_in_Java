import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
            properties.put("db.password","2");
            System.out.println(properties.getProperty("db.password"));
            System.out.println(properties.getProperty("db.username"));
            System.out.println(properties.getProperty("app.version"));
            System.out.println(properties.getProperty("app.name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File directory = new File("MyFolder");
        if (!directory.exists()){
//            directory.mkdirs();
            directory.mkdir();
        }
        for (int i = 0; i < 5 ; i++){
            File file = new File(directory+"\\tst_"+i+".txt");
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        File[] files = directory.listFiles();

        if (files != null) {

            for (File file : files) {
                System.out.println(file.getName());
            }
        }
        Nio();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        deleteFolder(directory);

    }
    public static void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if (files != null) {

            for (File file : files) {
                if (file.isDirectory()) deleteFolder(file);
            file.delete();
            }
        }
        System.out.println("delete them all!");
    }
    public static void Nio(){
        try {
            Files.writeString(Paths.get("data.txt"),"Hello World!");

            Path path = Paths.get("data.txt");
            String content = Files.readString(path);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}