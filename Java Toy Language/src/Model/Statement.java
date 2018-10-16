package Model;

import Utils.InterpreterException;

public interface Statement {
    ProgramState execute(ProgramState p) throws InterpreterException;
}
