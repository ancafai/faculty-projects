package Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InterpreterHeap<Value>  implements Heap<Value>, Serializable {
    //region Fields
    private Map<Integer, Value> heap;
    //endregion

    //region Constructor
    public InterpreterHeap(){
        heap = new HashMap<>();
    }
    //endregion

    //region Properties
    @Override
    public Map<Integer, Value> getContent() {
        return heap;
    }

    @Override
    public Value getContent(Integer address) {
        try{
            return heap.get(address);
        } catch (Exception e){
            throw new InterpreterException("Can't get inexistent value");
        }
    }

    @Override
    public void setContent(Map<Integer, Value> map) {
        heap.clear();
        heap.putAll(map);
    }

    @Override
    public Iterable<Map.Entry<Integer, Value>> getAll() {
        return heap.entrySet();
    }

    @Override
    public ArrayList<Value> getValues() {
        ArrayList<Value> values = new ArrayList<>();
        for(Map.Entry<Integer, Value> entry : heap.entrySet()){
            values.add(entry.getValue());
        }
        return values;
    }
    //endregion

    //region Methods
    @Override
    public int addContent(Value content) {
        int address = HeapAddressGenerator.generateId();
        heap.put(address, content);
        return address;
    }

    @Override
    public void addContent(Integer address, Value content) {
        heap.put(address, content);
    }

    @Override
    public boolean containsContent(Value content) {
        return heap.containsValue(content);
    }

    @Override
    public boolean containsKey(Integer address) {
        return heap.containsKey(address);
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        for (Map.Entry<Integer, Value> entry : heap.entrySet()) {
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
