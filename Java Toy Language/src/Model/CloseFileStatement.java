package Model;

import Utils.FileData;
import Utils.FileTable;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public class CloseFileStatement implements Statement, Serializable {
    //region Fields
    private Expression idDescriptionName;
    //endregion

    //region Constructor
    public CloseFileStatement(Expression idDescriptionName){
        this.idDescriptionName = idDescriptionName;
    }
    //endregion

    //region Methods
    public ProgramState execute(ProgramState p) throws InterpreterException {
        SymbolsTable<String, Integer> symbolsTable = p.getSymbolsTable();
        int result = idDescriptionName.evaluate(symbolsTable, p.getHeap());
        FileTable<Integer, FileData> fileTable = p.getFileTable();
        FileData fileData = fileTable.getFileData(result);
        BufferedReader buffer = fileData.getReader();
        try {
            buffer.close();
            fileTable.removeFile(result);
        }catch (IOException e){
            throw new InterpreterException("The file couldn't be closed");
        }
        return null;
    }

    public String toString(){
        return "Close file with id " + idDescriptionName;
    }
    //endregion
}
