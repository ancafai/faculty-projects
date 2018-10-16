package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class ArithmeticExpression implements Expression, Serializable {
    //region Fields
    private char operator;
    private Expression first;
    private Expression second;

    //endregion Fields

    //region Constructor
    public ArithmeticExpression(Expression first, Expression second, char operator) {
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    //endregion Constructor

    //region Methods
    public int evaluate(SymbolsTable<String, Integer> symbolsTable, Heap<Integer> heap) {
        int resultFirst = first.evaluate(symbolsTable, heap);
        int resultSecond = second.evaluate(symbolsTable, heap);
        switch (operator) {
            case '+':
                return resultFirst + resultSecond;
            case '-':
                return resultFirst - resultSecond;
            case '*':
                return resultFirst * resultSecond;
            case '/':
                if (resultSecond == 0)
                    throw new InterpreterException("Can't divide by 0");
                return resultFirst / resultSecond;
            default:
                throw new InterpreterException("Invalid Operator");
        }
    }

    public String toString() {
        return first.toString() + operator + second.toString();
    }
    //endregion Methods
}
