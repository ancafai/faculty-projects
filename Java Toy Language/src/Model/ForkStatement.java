package Model;

import Utils.SymbolsTable;
import Utils.InterpreterException;
import Utils.InterpreterExecutionStack;

public class ForkStatement implements Statement {
    //region Fields
    private Statement statement;
    //endregion

    //region Constructor
    public ForkStatement(Statement statement) {
        this.statement = statement;
    }
    //endregion

    //region Methods
    @Override
    public ProgramState execute(ProgramState p) throws InterpreterException {
        SymbolsTable<String, Integer> symbolsTable = p.getSymbolsTable();
        SymbolsTable clonedSymbolsTable = symbolsTable.clone();
        ProgramState newProgramState = new ProgramState(statement, new InterpreterExecutionStack<>(), clonedSymbolsTable, p.getOutput(), p.getFileTable(), p.getHeap());
        return newProgramState;
    }

    public String toString(){
        return "Fork(" + statement.toString() + ")";
    }
    //endregion
}
