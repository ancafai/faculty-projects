package Main;

import View.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUInterpreter extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ViewController viewController = new ViewController(primaryStage);
    }
}
