package Utils;

public class FileIdGenerator {
    //region Fields
    private static int counter = 0;
    //endregion

    //region Methods
    public static int generateId(){
        return ++counter;
    }
    //endregion
}
