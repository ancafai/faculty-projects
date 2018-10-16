package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class WriteHeapStatement implements Statement, Serializable {
    //region Fields
    private String varName;
    private Expression expression;
    //endregion

    //region Constructor
    public WriteHeapStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpreterException {
        SymbolsTable<String, Integer> symbolsTable = p.getSymbolsTable();
        Heap<Integer> heap = p.getHeap();

        int address = symbolsTable.getValue(varName);
        int content = expression.evaluate(symbolsTable, heap);

        heap.addContent(address, content);

        return null;
    }

    public String toString(){
        return "WriteHeap("+varName + ", " + expression.toString()+")";
    }
    //endregion
}
