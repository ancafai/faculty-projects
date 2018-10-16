package View;

import Controller.Controller;
import Utils.InterpreterException;

public class AllStepsCommand extends Command{
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public AllStepsCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute(){
        try{
            controller.executeAllSteps();
        }catch(InterpreterException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion
}
