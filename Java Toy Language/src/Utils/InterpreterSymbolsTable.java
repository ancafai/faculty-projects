package Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class InterpreterSymbolsTable<K, V> implements SymbolsTable<K, V>, Serializable {
    //region Fields
    private Map<K, V> symbolsTable;
    //endregion

    //region Constructor
    public InterpreterSymbolsTable(){
        this.symbolsTable = new HashMap<>();
    }
    //endregion

    //region Properties
    @Override
    public V getValue(K symbolName) throws InterpreterException {
        if(!symbolsTable.containsKey(symbolName))
            throw new InterpreterException("The Value of " + symbolName + " doesn't exist");
        return symbolsTable.get(symbolName);
    }

    @Override
    public void setValue(K symbolName, V value) throws InterpreterException {
        if(!symbolsTable.containsKey(symbolName))
            throw new InterpreterException("The Value of " + symbolName + "doesn't exist");
        symbolsTable.replace(symbolName, value);
    }

    @Override
    public int size() {
        return symbolsTable.size();
    }

    @Override
    public ArrayList<V> getValues() {
        ArrayList<V> values = new ArrayList<>();
        for(Map.Entry<K, V> entry : symbolsTable.entrySet()){
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public SymbolsTable<K, V> clone() {
        SymbolsTable newSymbolsTable = new InterpreterSymbolsTable<K, V>();
        for(Map.Entry<K, V> entry: getAll()){
            newSymbolsTable.addSymbol(entry.getKey(), entry.getValue());
        }
        return newSymbolsTable;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return symbolsTable.entrySet();
    }
    //endregion

    //region Methods
    @Override
    public void addSymbol(K symbolName, V value) {
        symbolsTable.put(symbolName, value);
    }

    @Override
    public boolean contains(K symbolName) {
        return symbolsTable.containsKey(symbolName);
    }

    @Override
    public String toString(){
        StringBuffer result = new StringBuffer();
        for (Map.Entry<K, V> entry : symbolsTable.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            result.append(key);
            result.append(" -> ");
            result.append(value);
            result.append("; ");
        }
        return result.toString();
    }
    //endregion
}
