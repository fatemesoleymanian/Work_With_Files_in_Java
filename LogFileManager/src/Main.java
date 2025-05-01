import java.nio.file.Paths;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LogManager logManager = new LogManager();
        logManager.setLogDirectory(Paths.get("logs"));
        logManager.setLogFile(logManager.getLogDirectory().resolve("log_"+ LocalDate.now().toString()+".txt"));
        logManager.createFolder();
        logManager.createFile();
        logManager.writeFile();
        logManager.readFile();
//        logManager.deleteFile();
//        logManager.copyFile();
//        logManager.moveFile();
        logManager.sortFile();
        logManager.filterFile();
    }
}