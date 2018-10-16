package Model;

import Utils.ExecutionStack;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class WhileStatement implements Statement, Serializable {
    //region Fields
    private Expression expression;
    private Statement statement;
    //endregion

    //region Constructor
    public WhileStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpreterException {
        ExecutionStack<Statement> exe = p.getExecutionStack();
        SymbolsTable<String, Integer> s = p.getSymbolsTable();
        if(expression.evaluate(s, p.getHeap()) != 0) {
            exe.push(this);
            exe.push(statement);
        }
        return null;
    }

    public String toString(){
        return "while("+expression.toString()+")" + statement.toString();
    }
    //endregion
}
