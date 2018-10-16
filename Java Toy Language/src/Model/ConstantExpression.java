package Model;

import Utils.Heap;
import Utils.SymbolsTable;

import java.io.Serializable;

public class ConstantExpression implements Expression, Serializable {
    //region Fields
    private int value;
    //endregion Fields

    //region Constructor
    public ConstantExpression(int value) {
        this.value = value;
    }
    //endregion

    //region Methods
    public int evaluate(SymbolsTable<String, Integer> symbolsTable, Heap<Integer> heap) {
        return value;
    }

    public String toString() {
        return "" + value;
    }
    //endregion
}
