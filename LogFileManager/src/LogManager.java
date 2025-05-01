import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LogManager {
    private Path logDirectory;
    private Path logFile;

    public Path getLogDirectory() {
        return logDirectory;
    }

    public LogManager setLogDirectory(Path logDirectory) {
        this.logDirectory = logDirectory;
        return this;
    }

    public Path getLogFile() {
        return logFile;
    }

    public LogManager setLogFile(Path logFile) {
        this.logFile = logFile;
        return this;
    }

    public void createFile(){
        try{
            if (Files.notExists(getLogFile())){
                Files.createFile(getLogFile());
                System.out.println("File is created");
            }

        }catch (IOException exception){
            System.out.println("Error in creating File");
            exception.printStackTrace();
        }
    }
    public void createFolder(){
        try{
            if (Files.notExists(getLogDirectory())){
                Files.createDirectories(getLogDirectory());
                System.out.println("Directory is created");
            }

        }catch (IOException exception){
            System.out.println("Error in creating directory");
            exception.printStackTrace();
        }
    }
    public void readFile(){
        Path logFile = Paths.get(getLogFile().toString());
        try {
            List<String> lines = Files.readAllLines(logFile);
            for (String line: lines) {
                System.out.println(line
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeFile(){
        Path logFile = Paths.get(getLogFile().toString());
        try {
            Files.write(logFile,"Hi World!".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }catch (IOException exception){
            System.out.println("Error in writing in file");
            exception.printStackTrace();
        }

    }
    public void deleteFile(){
        try {
            if (Files.exists(getLogFile())) {
                Files.delete(getLogFile());
                System.out.println("file is deleted");
            }
        } catch (IOException e) {
            System.out.println("error in deleting file");
            throw new RuntimeException(e);
        }
    }
    public void copyFile(){
        Path sourceFile = Paths.get(getLogFile().toString());
        Path destinationFile = Paths.get("logs","copy",getLogFile().getFileName().toString());
        try {
            Files.createDirectories(destinationFile.getParent());
            Files.copy(sourceFile,destinationFile,StandardCopyOption.REPLACE_EXISTING);
            System.out.println("copied !");
        } catch (IOException e) {
            System.out.println("Error in copying");
            throw new RuntimeException(e);
        }
    }
    public void moveFile(){
        Path sourceFile = Paths.get(getLogFile().toString());
        Path movedFile = Paths.get("logs","originals",getLogFile().getFileName().toString());
        try {
            Files.createDirectories(movedFile.getParent());
            Files.move(sourceFile,movedFile,StandardCopyOption.REPLACE_EXISTING);
            System.out.println("moved !");
        } catch (IOException e) {
            System.out.println("Error in moving");
            throw new RuntimeException(e);
        }
    }
    public void sortFile(){
        Path logsDirectory = Paths.get("logs");
        try {
            List<Path> files = Files.walk(logsDirectory)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            files.sort(Comparator.comparingLong((p -> p.toFile().lastModified())));
            files.stream()
                    .forEach(f -> System.out.println(f.getFileName()));
        } catch (IOException e) {
            System.out.println("Error in sort");
            throw new RuntimeException(e);
        }
    }
    public void filterFile(){
        Path logsDirectory = Paths.get("logs");
        try {
            List<Path> files = Files.walk(logsDirectory)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            Date treshholdDate = new Date(System.currentTimeMillis() - 86400000L);

            files.stream()
                    .filter(f -> new Date(f.toFile().lastModified()).after(treshholdDate))
                    .forEach(f -> System.out.println(f.getFileName()));
        } catch (IOException e) {
            System.out.println("Error in sort");
            throw new RuntimeException(e);
        }
    }

}
