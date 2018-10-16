package Repository;

import Model.ProgramState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Repository {
    void addProgramState(ProgramState programState);
    void setProgramState(ProgramState programState);
    ProgramState getInitialProgram();
    List<ProgramState> getProgramStates();
    void setProgramStates(List<ProgramState> newProgramStates);
    void logProgramStateExecute(ProgramState programState) throws IOException;
    void serialize(ProgramState programState, String fileName);
    ProgramState deserialize(String fileName);
}
