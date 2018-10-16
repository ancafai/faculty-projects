package View;
import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgramPanel implements Initializable{
    //region Fields
    @FXML
    private ListView<Statement> allPrograms;
    @FXML
    private Button executeButton;
    private Statement selectedStatement;
    //endregion

    //region Initialization
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateAllProgramsList();
        configurateListView();
        openExecutionWindow();
    }
    //endregion

    //region Methods
    private void configurateListView() {
        allPrograms.setCellFactory(new Callback<ListView<Statement>, ListCell<Statement>>() {
            @Override
            public ListCell<Statement> call(ListView<Statement> param) {
                ListCell<Statement> cell = new ListCell<Statement>(){
                    @Override
                    protected void updateItem(Statement item, boolean empty){
                        super.updateItem(item, empty);
                        if(item!=null) setText(item.toString());
                    }
                };
                return cell;
            }
        });

        allPrograms.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Statement>() {
            @Override
            public void changed(ObservableValue<? extends Statement> observable, Statement oldValue, Statement newValue) {
                selectedStatement = newValue;
                System.out.println(selectedStatement);
            }
        });
    }

    private void openExecutionWindow() {

        executeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (selectedStatement == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No Program was selected!");
                    alert.showAndWait();
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("execution_panel.fxml"));
                        GridPane executionPanel = fxmlLoader.load();
                        ExecutionPanel control = fxmlLoader.getController();
                        Stage secondStage = new Stage();
                        secondStage.setScene(new Scene(executionPanel));
                        control.setStage(secondStage);
                        control.setOriginalProgram(selectedStatement);
                        control.createProgramState();
                        control.populate();
                        control.configure();
                        secondStage.setTitle("" + selectedStatement);
                        secondStage.show();
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } catch (IOException e) {
                        System.out.println("Couldn't open Execution Panel");
                    }
                }
            }
        });
    }

    private void populateAllProgramsList() {
        //Normal Program
        Statement workingInMemory = new IfStatement(new ConstantExpression(0), new CompoundStatement(new AssignStatement("x", new ConstantExpression(2)), new PrintStatement(new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(4), '+'))), new CompoundStatement(new AssignStatement("a", new ConstantExpression(10)), new PrintStatement(new VariableExpression("a"))));

        //File Program
        Statement file = new CompoundStatement(new CompoundStatement(new OpenFileStatement("test.txt", "fileVar"), new ReadFileStatement(new ConstantExpression(1), "a")), new CloseFileStatement(new ConstantExpression(1)));

        //Heap Program
        Statement heapStatement = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new NewStatement("v", new ConstantExpression(20))), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new WriteHeapStatement("a", new ConstantExpression(30)))), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new ReadHeapExpression("a")))), new AssignStatement("a", new ConstantExpression(0)));

        //Thread Program
        Statement threadStatement = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)), new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a")))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a"))))));
        
      //For Program

        Statement forStatement = new CompoundStatement(new AssignStatement("v", new ConstantExpression(20)),
                new CompoundStatement(
                    new ForStatement(new AssignStatement("v", new ConstantExpression(0)), new ComparisonExpression(new VariableExpression("v"), new ConstantExpression(3), "<"), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), '+')),
                            new ForkStatement(
                                    new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                            new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), '+'))
                                    )
                            )
                    ),
                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), '*')
                        )
                )
        );
        
        //Sleep Program
        Statement sleepStatement = new CompoundStatement(new AssignStatement("v", new ConstantExpression(0)),
        		new CompoundStatement 
        		(new WhileStatement(new ComparisonExpression(new VariableExpression("v"), new ConstantExpression(3), "<"),
        				new CompoundStatement (new ForkStatement( new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), '+')))),
        					new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), '+')))),
        					new CompoundStatement(new SleepStatement(new ConstantExpression(15)), 
        							new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), '*')))
        					));
        
     
        
        ObservableList<Statement> programsObservable = FXCollections.observableArrayList();
        programsObservable.addAll(workingInMemory, file, heapStatement, threadStatement, forStatement, sleepStatement);
        allPrograms.setItems(programsObservable);
        System.out.println(allPrograms.toString());
        
        
    }
    //endregion
}
