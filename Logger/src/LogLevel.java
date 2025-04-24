public enum LogLevel {
    INFO(1),
    WARNING(2),
    ERROR(3);

    private final int severity;

    LogLevel(int severity){
        this.severity = severity;
    }
    public int getSeverity(){
        return severity;
    }
}
