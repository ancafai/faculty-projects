package View;

public class ExitCommand extends Command {
    //region Constructor
    public ExitCommand(String key, String description){
        super(key, description);
    }
    //endregion

    //region Methods
    @Override
    public void execute(){
        System.exit(0);
    }
    //endregion
}
