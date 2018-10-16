package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class ReadHeapExpression implements Expression, Serializable {
    //region Fields
    private String variableName;
    //endregion

    //region Constructor
    public ReadHeapExpression(String variableName) {
        this.variableName = variableName;
    }
    //endregion

    //region Methods
    @Override
    public int evaluate(SymbolsTable<String, Integer> symbolsTable, Heap<Integer> heap) throws InterpreterException {
        int address = symbolsTable.getValue(variableName);
        return heap.getContent(address);
    }

    public String toString(){
        return "ReadHeap("+variableName+")";
    }
    //endregion
}
