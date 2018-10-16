package Utils;

import java.util.ArrayList;
import java.util.Map;

public interface BarrierTable<Value> {
    int addBarrier(Value content);
    void addBarrier(Integer address, Value content);
    void setBarrierTable(Map<Integer, Value> map);
    Value getBarrier(Integer address);
    void setBarrier(int address, Value value);
    boolean containsKey(Integer address);
    boolean containsBarrier(Value content);
    boolean isEmpty();
    Iterable<Map.Entry<Integer, Value>> getAll();
    Map<Integer, Value> getContent();
    ArrayList<Value> getValues();
}
