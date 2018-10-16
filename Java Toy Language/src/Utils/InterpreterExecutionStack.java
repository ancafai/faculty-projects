package Utils;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class InterpreterExecutionStack<E> implements ExecutionStack<E>, Serializable {
    //region Fields
    private Deque<E> executionStack;
    //endregion Fields

    //region Constructor
    public InterpreterExecutionStack(){
        this.executionStack =  new ArrayDeque<>();
    }
    //endregion

    //region Properties
    @Override
    public E top() throws InterpreterException {
        if(executionStack.isEmpty())
            throw new InterpreterException("The Execution Stack is empty");
        return executionStack.peek();
    }

    @Override
    public Iterable<E> getAll() {
        return executionStack;
    }
    //endregion

    //region Methods
    @Override
    public void push(E statement) {
        executionStack.push(statement);
    }

    @Override
    public E pop() throws InterpreterException {
        if(executionStack.isEmpty())
            throw new InterpreterException("The Execution Stack is empty");
        return executionStack.pop();
    }

    @Override
    public boolean isEmpty() {
        if(executionStack.isEmpty())
            return true;
        return false;
    }

    @Override
    public String toString(){
        return executionStack.toString();
    }
    //endregion
}
