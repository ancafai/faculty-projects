package Utils;

public class ProgramIdGenerator {
    //region Fields
    private static int fileTableId = 0, heapId = 0, ProgramStateId = 0;
    //endregion

    //region Methods
    public static int generateProgramStateId(){
        return ProgramStateId += 1;
    }
    public static int generateFileTableId(){
        return ++fileTableId;
    }
    public static int generateHeapId(){
        return ++heapId;
    }
    //endregion
}
