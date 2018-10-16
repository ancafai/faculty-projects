package Model;

import Utils.ExecutionStack;
import Utils.InterpreterException;
import Utils.Output;
import Utils.SymbolsTable;


public class PrintScrStatement implements Statement {
    //region Fields
    private Expression expression;
    //endregion

    //region Constructor
    public PrintScrStatement(Expression expression){
        this.expression = expression;
    }

    //region Methods
    public ProgramState execute(ProgramState p)  {
        Output<Integer> o = p.getOutput();
        SymbolsTable<String, Integer> s = p.getSymbolsTable();
        o.add(expression.evaluate(s, p.getHeap()));
        System.out.println("(" + p.getId() + ", " + expression.evaluate(s, p.getHeap()) + ")");
        return null;
    }
    
    public String toString(){
        return "print(" + expression.toString()+")";
    }
    //endregion
}
