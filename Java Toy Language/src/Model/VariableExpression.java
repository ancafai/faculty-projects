package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class VariableExpression implements Expression, Serializable {
    //region Fields
    private String name;
    //endregion

    //region Constructor
    public VariableExpression(String name){
        this.name = name;
    }
    //endregion

    //region Methods
    public int evaluate(SymbolsTable<String, Integer> symbolsTable, Heap<Integer> heap) throws InterpreterException {
        return symbolsTable.getValue(name);
    }
    public String toString(){
        return name;
    }
    //endregion
}
