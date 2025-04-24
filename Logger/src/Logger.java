import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Logger {

    private long maxSize = 400;
    private String baseFolderName = "logs";
    private String baseFileName = baseFolderName+"\\"+LocalDate.now().toString()+"_log.txt";

    private File logsFile = null;
    private final DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LogLevel minLogLevel = LogLevel.INFO;
    private FormatType formatType = FormatType.TEXT;

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
    private void ensureLogDirectoryExists(){
        File dir = new File(baseFolderName);
        if (!dir.exists()) dir.mkdirs();
    }
    public void setMaxSize(long size){
        this.maxSize = size;
    }
    public void setMinLogLevel(LogLevel level){
        this.minLogLevel = level;
    }
    public void setFormatType(FormatType formatType){
        this.formatType = formatType;
    }

    private void addLog(String log, LogLevel level, String color){

        if (level.getSeverity() < minLogLevel.getSeverity()) return;

        try {
            rotateIfNeeded();
            String message = wrapMsg(level,log);

            /** if you need more speed use BufferWriter*/
            try (FileWriter writer = new FileWriter(this.logsFile, true)) {
                writer.write(message);
                System.out.println(color+message);
            } catch (IOException e) {
                System.out.println("Error in writing log");
                e.printStackTrace();
            }
        }catch (Exception e) {
            System.out.println("Error in writing log2");
            e.printStackTrace();
        }
    }
    private String wrapMsg(LogLevel level,String log){
        String msg = "";
        String time = LocalDateTime.now().format(this.formatter);

        switch (this.formatType){
            case TEXT:
                msg = time + " - " + level.name() + " - " + log + System.lineSeparator();
                break;
            case JSON:
                msg = String.format("{\"timestamp\":\"%s\",\"level\":\"%s\",\"message\":\"%s\"}",
                        time,level.name(),escapeJson(log));
        }
        return msg;
    }
    private String escapeJson(String s){
        return s.replace("\"","\\\"").replace("\n","\\n");
    }
    private void rotateIfNeeded(){
       File logFile = new File(baseFileName);

       if (logFile.exists() && logFile.length() >= this.maxSize){
           int index = 1;
           File rotatedFile;
           do{
               rotatedFile = new File(baseFolderName+"\\"+LocalDate.now().toString()+"_log_"+index+".txt");
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

    public void zipLogs() throws IOException {
        String zipFileName = baseFolderName+".zip";
        Path zipPath = Paths.get(zipFileName);

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            Files.walk(Paths.get(baseFolderName))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            ZipEntry zipEntry = new ZipEntry(baseFolderName+"/" + path.getFileName().toString());
                            zos.putNextEntry(zipEntry);
                            Files.copy(path, zos);
                            zos.closeEntry();
                        } catch (IOException e) {
                            System.err.println("Error in Compressing..." + path + " -> " + e.getMessage());
                        }
                    });
        }

        System.out.println("Files are compressed!");
    }

}
