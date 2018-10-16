package View;

import Controller.Controller;

public class AllStepsForAllProgramsCommand extends Command{
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public AllStepsForAllProgramsCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute(){
        try{
            controller.allStepsForAllPrograms();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //endregion{
}
