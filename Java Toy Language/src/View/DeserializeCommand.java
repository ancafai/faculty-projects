package View;

import Controller.Controller;
import Utils.InterpreterException;

import java.util.Scanner;

public class DeserializeCommand extends Command {
    //region Fields
    private Controller controller;
    //endregion

    //region Constructor
    public DeserializeCommand(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }
    //endregion

    //region Methods
    @Override
    public void execute() {
        System.out.print("Give a file from where you'd like to deserialize: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try{
            controller.deserialize(fileName);
        } catch (InterpreterException e){
            System.out.println(e.getMessage());
        }
    }
    //endregion
}
