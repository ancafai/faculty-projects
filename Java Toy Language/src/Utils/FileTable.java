package Utils;

import java.util.Map;

public interface FileTable<Key, Value> {
    void addFile(Key key, Value value);
    Value getFileData(Key key) throws InterpreterException;
    void removeFile(Key key) throws InterpreterException;
    Iterable<Map.Entry<Key, Value>> getAllFiles();
}
