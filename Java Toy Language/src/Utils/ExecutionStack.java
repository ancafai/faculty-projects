package Utils;

public interface ExecutionStack<E> {
    void push(E element);
    E pop() throws InterpreterException;
    boolean isEmpty();
    E top() throws InterpreterException;
    Iterable<E> getAll();
}
