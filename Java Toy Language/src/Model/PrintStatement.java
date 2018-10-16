package Model;

import Utils.Output;
import Utils.SymbolsTable;

import java.io.Serializable;

public class PrintStatement implements Statement, Serializable {
    //region Fields
    private Expression expression;
    //endregion

    //region Constructor
    public PrintStatement(Expression expression){
        this.expression = expression;
    }
    //endregion

    //region Methods
    public ProgramState execute(ProgramState p){
        Output<Integer> o = p.getOutput();
        SymbolsTable<String, Integer> s = p.getSymbolsTable();
        o.add(expression.evaluate(s, p.getHeap()));
        return null;
    }
    public String toString(){
        return "print(" + expression.toString()+")";
    }
    //endregion
}
