package View;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {
    //region Fields
    private Map<String, Command> commands;
    //endregion

    //region Constructor
    public TextMenu(){
        commands = new HashMap<>();
    }
    //endregion

    //region Methods
    public void addCommand(Command command){
        commands.put(command.getKey(), command);
    }

    private void printMenu(){
        for(Command command: commands.values()){
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("Input a command: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if(command == null){
                System.out.println("Invalid command");
                continue;
            }
            command.execute();
        }
    }
    //endregion
}
