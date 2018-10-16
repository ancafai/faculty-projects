package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {
    //region Fields
    public static Stage primaryStage;
    //endregion

    //region Controller
    public ViewController(Stage stage){
        primaryStage = stage;
        initGUI(stage);
    }
    //endregion


    private void initGUI(Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewController.class.getResource("program_panel.fxml"));
        try{
            HBox programHorizontalBox = loader.load();
            Scene programScene = new Scene(programHorizontalBox);
            stage.setScene(programScene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
