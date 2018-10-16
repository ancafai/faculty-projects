package Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InterpreterOutput<E> implements Output<E>, Serializable {
    //region Fields
    private List<E> outputList;
    //endregion

    //region Constructor
    public InterpreterOutput(){
        this.outputList = new ArrayList<E>();
    }
    //endregion

    //region Properties
    @Override
    public int size(){
        return outputList.size();
    }

    @Override
    public Iterable<E> getAll() {
        return outputList;
    }
    //endregion

    //region Methods
    @Override
    public void add(E value) {
        outputList.add(value);
    }

    @Override
    public String toString(){
        return outputList.toString();
    }
    //endregion Methods
}
