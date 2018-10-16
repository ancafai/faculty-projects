package Model;

import Utils.ExecutionStack;

import java.io.Serializable;

public class CompoundStatement implements Statement, Serializable {
    //region Fields
    private Statement first;
    private Statement second;
    //endregion Fields

    //region Constructor
    public CompoundStatement(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }
    //endregion Constructor

    //region Methods
    public ProgramState execute(ProgramState p) {
        ExecutionStack<Statement> exe = p.getExecutionStack();
        exe.push(second);
        exe.push(first);
        return null;
    }

    public String toString() {
        return "{" + first.toString() + "; " + second.toString() + "}";
    }
    //endregion Methods
}
