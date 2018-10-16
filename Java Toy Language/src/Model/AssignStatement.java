package Model;

import Utils.Heap;
import Utils.SymbolsTable;

import java.io.Serializable;

public class AssignStatement implements Statement, Serializable {
    //region Fields
    private String variable;
    private Expression expression;

    //endregion Fields

    //region Constructor
    public AssignStatement(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    //endregion Constructor

    //region Methods
    public ProgramState execute(ProgramState p) {
        Heap<Integer> heap = p.getHeap();
        SymbolsTable<String, Integer> t = p.getSymbolsTable();
        int result = expression.evaluate(t, heap);
        t.addSymbol(variable, result);
        return null;
    }

    public String toString() {
        return variable + " = " + expression.toString();
    }
    //endregion Methods
}
