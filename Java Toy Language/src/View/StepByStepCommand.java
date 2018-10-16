package View;

import Controller.Controller;
import Utils.InterpreterException;

public class StepByStepCommand extends Command{
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public StepByStepCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute(){
        try{
            controller.executeOneStep(controller.getInitialProgram());
        }catch(InterpreterException e){
            System.out.print(e.getMessage());
        }
    }
    //endregion
}
