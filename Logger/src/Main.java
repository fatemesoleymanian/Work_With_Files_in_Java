public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
//        Logger logger = new Logger("log.log");

        /**Filter logs */
        logger.setMinLogLevel(LogLevel.WARNING);

        logger.info("App starts...");
        logger.warning("Storage is running out!");
        logger.error("Failed to fetch");
    }
}