package Utils;

import java.util.ArrayList;
import java.util.Map;

public interface Heap<Value> {
    int addContent(Value content);
    void addContent(Integer address, Value content);
    void setContent(Map<Integer, Value> map);
    Value getContent(Integer address);
    boolean containsKey(Integer address);
    boolean containsContent(Value content);
    boolean isEmpty();
    Iterable<Map.Entry<Integer, Value>> getAll();
    Map<Integer, Value> getContent();
    ArrayList<Value> getValues();
}
