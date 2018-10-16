package Model;

import Utils.Heap;
import Utils.InterpreterException;
import Utils.SymbolsTable;

import java.io.Serializable;

public class ComparisonExpression implements Expression, Serializable {
    //region Fields
    private Expression firstExpression;
    private Expression secondExpression;
    private String operator;
    //endregion

    //region Constructor
    public ComparisonExpression(Expression firstExpression, Expression secondExpression, String operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }
    //endregion

    //region Methods
    @Override
    public int evaluate(SymbolsTable<String, Integer> symbolsTable, Heap<Integer> heap) throws InterpreterException {
        int resultFirst = firstExpression.evaluate(symbolsTable, heap);
        int resultSecond = secondExpression.evaluate(symbolsTable, heap);
        switch (operator) {
            case "<":
                 if(resultFirst < resultSecond)
                     return 1;
                 else
                     return 0;
            case "<=":
                if(resultFirst <= resultSecond)
                    return 1;
                else
                    return 0;
            case ">":
                if(resultFirst > resultSecond)
                    return 1;
                else
                    return 0;
            case ">=":
                if(resultFirst >= resultSecond)
                    return 1;
                else
                    return 0;
            case "==":
                if(resultFirst == resultSecond)
                    return 1;
                else
                    return 0;
            case "!=":
                if(resultFirst != resultSecond)
                    return 1;
                else
                    return 0;
            default:
                throw new InterpreterException("Invalid Operator");
        }
    }

    public String toString(){
        return firstExpression.toString() + operator + secondExpression.toString();
    }
    //endregion
}
