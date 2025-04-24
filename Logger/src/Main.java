import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Hi from logger!");
//        System.out.println("To setup your logger fill these fields (enter if you don't need to change):");
//        System.out.print("Maximum size(in bytes) of files (default is 400 bytes): ");
//        long maxSize = scanner.nextLong();
//        System.out.println("Filter logs: 1.Information  2.Warnings  3.Errors (default is no filter)");
//        int logLevel = scanner.nextInt();
//        System.out.println("Format types : 1.txt  2.json");
//        int format = scanner.nextInt();



        Logger logger = new Logger();
//        Logger logger = new Logger("log.log");

        /**Filter logs */
//        logger.setMinLogLevel(LogLevel.WARNING);
//        logger.setFormatType(FormatType.JSON);
//        logger.setMaxSize(400);

        logger.info("App starts...");
        logger.warning("Storage is running out!");
        logger.error("Failed to fetch");

//        System.out.println("To get log compressed enter y and if you don't ");
        try {
            logger.zipLogs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}