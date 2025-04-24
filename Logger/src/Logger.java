import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private String baseFolderName = "logs";
    private String baseFileName = baseFolderName+"\\"+LocalDate.now().toString()+"_log.txt";

    private File logsFile = null;
    private final DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LogLevel minLogLevel = LogLevel.INFO;
    private void ensureLogDirectoryExists(){
        File dir = new File(baseFolderName);
        if (!dir.exists()) dir.mkdirs();
    }
    public Logger(){
        ensureLogDirectoryExists();
        this.logsFile = new File(baseFileName);

        try {
            if (this.logsFile.createNewFile()) {
                this.info("file created");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Logger(String baseFileName){
        ensureLogDirectoryExists();
        this.baseFileName = baseFileName;

        this.logsFile = new File(this.baseFileName);

        try {
            if (this.logsFile.createNewFile()) {
                this.info("file created");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setMinLogLevel(LogLevel level){
        this.minLogLevel = level;
    }
    private void addLog(String log, LogLevel level, String color){

        if (level.getSeverity() < minLogLevel.getSeverity()) return;

        try {
            rotateIfNeeded();
            String time = LocalDateTime.now().format(this.formatter);

            /** if you need more speed use BufferWriter*/
            try (FileWriter writer = new FileWriter(this.logsFile, true)) {
                writer.write(time + " - " + level.name() + " - " + log + System.lineSeparator());
                System.out.println(color+time + " - " + level.name() + " - " + log + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error in writing log");
                e.printStackTrace();
            }
        }catch (Exception e) {
            System.out.println("Error in writing log2");
            e.printStackTrace();
        }
    }
    private void rotateIfNeeded(){
       File logFile = new File(baseFileName);

       if (logFile.exists() && logFile.length() >= 400){
           int index = 1;
           File rotatedFile;
           do{
               rotatedFile = new File("logs\\"+LocalDate.now().toString()+"_log_"+index+".txt");
               index++;
           }while (rotatedFile.exists());

           try {
               /** Important */
               Files.move(logFile.toPath(), rotatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
    }
    public void info(String message){
        addLog(message,LogLevel.INFO,"\u001B[32m");
    }
    public void error(String message){
        addLog(message,LogLevel.ERROR,"\u001B[31m");
    }
    public void warning(String message){
        addLog(message,LogLevel.WARNING,"\u001B[33m");
    }

}
