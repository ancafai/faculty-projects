package Model;

import Utils.ExecutionStack;
import Utils.SymbolsTable;

import java.io.Serializable;

public class IfStatement implements Statement, Serializable {
    //region Fields
    private Expression expression;
    private Statement first;
    private Statement second;
    //endregion Fields

    //region Constructor
    public IfStatement(Expression expression, Statement first, Statement second){
        this.expression = expression;
        this.first = first;
        this.second = second;
    }
    //endregion Constructor

    //region Methods
    public ProgramState execute(ProgramState p){
        ExecutionStack<Statement> exe = p.getExecutionStack();
        SymbolsTable<String, Integer> s = p.getSymbolsTable();
        if(expression.evaluate(s, p.getHeap()) != 0)
            exe.push(first);
        else
            exe.push(second);
        return null;
    }

    public String toString(){
        return "if(Evaluate( "+expression+" ) then: " + first.toString() + "; else: " + second.toString();
    }
    //endregion Methods
}
