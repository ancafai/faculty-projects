package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class InterpreterBarrierTable<Value > implements BarrierTable<Value> {
    //region Fields
    private Map<Integer, Value> barrierTable;
    //endregion

    //region Constructor
    public InterpreterBarrierTable(){
        barrierTable = Collections.synchronizedMap(new HashMap<>());
    }
    //endregion

    //region Properties
    @Override
    public Map<Integer, Value> getContent() {
        return barrierTable;
    }

    @Override
    public Value getBarrier(Integer address) {
        try{
            return barrierTable.get(address);
        } catch (Exception e){
            throw new InterpreterException("Can't get inexistent value");
        }
    }

    @Override
    public void setBarrierTable(Map<Integer, Value> map) {
        barrierTable.clear();
        barrierTable.putAll(map);
    }

    @Override
    public void setBarrier(int address, Value value){
        synchronized (barrierTable) {
            barrierTable.put(address, value);
        }
    }

    @Override
    public Iterable<Map.Entry<Integer, Value>> getAll() {
        return barrierTable.entrySet();
    }

    @Override
    public ArrayList<Value> getValues() {
        ArrayList<Value> values = new ArrayList<>();
        for(Map.Entry<Integer, Value> entry : barrierTable.entrySet()){
            values.add(entry.getValue());
        }
        return values;
    }
    //endregion

    //region Methods
    @Override
    public int addBarrier(Value content) {
        int address = BarrierAddressGenerator.generateId();
        synchronized (barrierTable) {
            barrierTable.putIfAbsent(address, content);
        }
        return address;
    }

    @Override
    public void addBarrier(Integer address, Value content) {
        barrierTable.put(address, content);
    }

    @Override
    public boolean containsBarrier(Value content) {
        return barrierTable.containsValue(content);
    }

    @Override
    public boolean containsKey(Integer address) {
        synchronized (barrierTable) {
            return barrierTable.containsKey(address);
        }
    }

    @Override
    public boolean isEmpty() {
        return barrierTable.isEmpty();
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        for (Map.Entry<Integer, Value> entry : barrierTable.entrySet()) {
            Integer key = entry.getKey();
            Value value = entry.getValue();
            result.append(key);
            result.append(" : ");
            result.append(value);
            result.append("; ");
        }
        return result.toString();
    }
    //endregion
}
