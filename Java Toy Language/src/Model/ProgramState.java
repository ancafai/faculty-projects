package Model;

import Utils.*;

import java.io.Serializable;

public class ProgramState implements Serializable {
    //region Fields
    private int Id;
    private Statement initialProgram;
    private ExecutionStack<Statement> executionStack;
    private SymbolsTable<String, Integer> symbolsTable;
    private Output<Integer> output;
    private FileTable<Integer, FileData> fileTable;
    private Heap<Integer> heap;
    //endregion

    //region Constructor
    public ProgramState(Statement initialProgram, ExecutionStack<Statement> executionStack, SymbolsTable<String, Integer> symbolsTable, Output<Integer> output, FileTable<Integer, FileData> fileTable, Heap<Integer> heap){
        this.Id = ProgramIdGenerator.generateProgramStateId();
        this.initialProgram = initialProgram;
        this.executionStack = executionStack;
        this.symbolsTable = symbolsTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        executionStack.push(initialProgram);
    }
    //endregion

    //region Properties
    public int getId() {
        return Id;
    }

    public Statement getInitialProgram() {
        return initialProgram;
    }

    public FileTable<Integer, FileData> getFileTable(){return fileTable;}

    public ExecutionStack<Statement> getExecutionStack() {
        return executionStack;
    }

    public SymbolsTable<String, Integer> getSymbolsTable() {
        return symbolsTable;
    }

    public Heap<Integer> getHeap() { return heap; }

    public Output<Integer> getOutput() {
        return output;
    }
    

    
    //endregion

    //region Methods
    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep(){
        if(executionStack.isEmpty()){
            throw new InterpreterException("The program has finished execution");
        }
        Statement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public String toString(){
        return "Program " + Id + ":\n"+ "Execution Stack: " + executionStack.toString() + ";\n" + "Symbols Table: " + symbolsTable.toString() + "\n" + "Output: " + output.toString() + ";\nFileTable\n" + fileTable.toString() + ";\nHeap\n" + heap.toString() +  "\n---------------\n";
    }
    //endregion
}
