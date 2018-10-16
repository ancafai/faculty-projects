package Utils;

/**
 * Created by CristianCosmin on 25.01.2017.
 */
public class LockAddressGenerator {
    //region Fields
    private static int counter = 0;
    //endregion

    //region Methods
    public static int generateId(){
        return ++counter;
    }
    //endregion
}
