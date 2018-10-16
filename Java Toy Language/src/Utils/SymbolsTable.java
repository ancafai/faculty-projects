package Utils;

import java.util.ArrayList;
import java.util.Map;

public interface SymbolsTable<K, V> {
    void addSymbol(K symbolName, V value);
    boolean contains(K symbolName);
    V getValue(K symbolName) throws InterpreterException;
    void setValue(K symbolName, V value) throws InterpreterException;
    int size();
    Iterable<Map.Entry<K,V>> getAll();
    ArrayList<V> getValues();
    SymbolsTable<K, V> clone();
}
