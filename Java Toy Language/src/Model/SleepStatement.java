package Model;

import Utils.ExecutionStack;
import Utils.SymbolsTable;

import java.io.Serializable;

public class SleepStatement implements Statement, Serializable {
    //region Fields
    private Expression number;
    //endregion Fields

    //region Constructor
    public SleepStatement(Expression number){
        this.number = number;
    }
    //endregion Constructor

    //region Methods
    public ProgramState execute(ProgramState p){
        ExecutionStack<Statement> exe = p.getExecutionStack();
        SymbolsTable<String, Integer> s = p.getSymbolsTable();
        if(number.evaluate(s, p.getHeap()) != 0)
            exe.push(new SleepStatement(new ConstantExpression(number.evaluate(s, p.getHeap()) - 1)));
        return null;
    }

    public String toString(){
        return "sleep( " +number+" )";
    }
    //endregion Methods
}
