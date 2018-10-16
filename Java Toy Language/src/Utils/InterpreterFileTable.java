package Utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InterpreterFileTable<Key, Value> implements FileTable<Key, Value>, Serializable {
    //region Fields
    private Map<Key, Value> files;
    //endregion Fields

    //region Constructor
    public InterpreterFileTable(){
        files = new HashMap<>();
    }
    //endregion Constructor

    //region Properties
    @Override
    public Value getFileData(Key fileId) throws InterpreterException {
        if(!files.containsKey(fileId))
            throw new InterpreterException(fileId + " doesn't exist");
        return files.get(fileId);
    }

    @Override
    public Iterable<Map.Entry<Key, Value>> getAllFiles() {
        return files.entrySet();
    }
    //endregion

    //region Methods
    @Override
    public void addFile(Key fileId, Value fileData) {
        files.putIfAbsent(fileId, fileData);
    }

    @Override
    public void removeFile(Key fileId) throws InterpreterException {
        if(!files.containsKey(fileId))
            throw new InterpreterException(fileId + " doesn't exist");
        files.remove(fileId);
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        for(Map.Entry<Key, Value> entry : files.entrySet()) {
            Key key = entry.getKey();
            Value value = entry.getValue();
            result.append(key);
            result.append(" = ");
            result.append(value);
            result.append("; ");
        }
        return result.toString();
    }
    //endregion Methods
}
