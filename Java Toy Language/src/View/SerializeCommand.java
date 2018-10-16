package View;

import Controller.Controller;
import Utils.InterpreterException;

import java.util.Scanner;

public class SerializeCommand extends Command {
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public SerializeCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute() {
        System.out.print("Give a file where you'd like to save your serialized information: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try{
            controller.serialize(controller.getInitialProgram(), fileName);
        } catch (InterpreterException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion
}
