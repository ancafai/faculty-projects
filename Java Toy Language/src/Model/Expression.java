package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

public interface Expression {
    int evaluate(SymbolsTable<String, Integer> symbolsTable, Heap<Integer> heap) throws InterpreterException;
}
