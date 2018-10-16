package Model;

import Utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

public class ReadFileStatement implements Statement, Serializable {
    //region Fields
    private Expression idDescriptionName;
    private String varName;
    //endregion Fields

    //region Constructor
    public ReadFileStatement(Expression idDescriptionName, String varName){
        this.idDescriptionName = idDescriptionName;
        this.varName = varName;
    }
    //endregion Constructor

    //region Methods

    public ProgramState execute(ProgramState p) throws InterpreterException {
        SymbolsTable<String, Integer> s = p.getSymbolsTable();
        Integer id = idDescriptionName.evaluate(s, p.getHeap());
        FileTable<Integer, FileData> fileTable= p.getFileTable();
        FileData fileData = fileTable.getFileData(id);
        try {
            BufferedReader buffer = fileData.getReader();
            String line = buffer.readLine();
            int result;
            if(line != null){
                result = Integer.parseInt(line);
            }
            else{
                result = 0;
            }
            s.addSymbol(varName, result);
        }catch(IOException e){
            throw new InterpreterException("File couldn't be read");
        }
        return null;
    }

    public String toString(){
        return "Read file from " + varName;
    }
    //endregion
}
