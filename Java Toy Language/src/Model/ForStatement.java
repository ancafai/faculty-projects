package Model;

import Utils.ExecutionStack;
import Utils.InterpreterException;

public class ForStatement implements Statement {
    private Statement initial;
    private Expression condition;
    private Statement statement;
    private Statement finalStatement;

    public ForStatement(Statement initial, Expression condition, Statement statement, Statement finalStatement) {
        this.initial = initial;
        this.condition = condition;
        this.statement = statement;
        this.finalStatement = finalStatement;
    }

    @Override
    public ProgramState execute(ProgramState p) throws InterpreterException {
        ExecutionStack<Statement> executionStack = p.getExecutionStack();
        executionStack.push(new WhileStatement(condition,new CompoundStatement(finalStatement,statement)));
        executionStack.push(initial);
        return null;
    }

    @Override
    public String toString(){
        return "for("+initial.toString()+";"+condition.toString()+";"+statement.toString()+")"+finalStatement.toString();
    }
}
