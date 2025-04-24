public enum FormatType {
    TEXT(1),
    JSON(2);

    private final int format;

    FormatType(int format){
        this.format = format;
    }
    public int getformat(){
        return format;
    }
}
