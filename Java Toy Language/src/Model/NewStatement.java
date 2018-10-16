package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class NewStatement implements Statement, Serializable {
    //region Fields
    private String variableName;
    private Expression expression;
    //endregion

    //region Constructor
    public NewStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpreterException {
        Heap<Integer> heap = p.getHeap();
        SymbolsTable<String, Integer> symbolsTable = p.getSymbolsTable();

        int evaluation = expression.evaluate(symbolsTable, p.getHeap());
        int address = heap.addContent(evaluation);
        symbolsTable.addSymbol(variableName, address);

        return null;
    }

    public String toString(){
        return "new("+variableName+", " + expression.toString() + ")";
    }
    //endregion
}
