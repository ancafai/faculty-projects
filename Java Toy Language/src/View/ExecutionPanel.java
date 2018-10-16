package View;

import Controller.Controller;
import Model.*;
import Repository.*;
import Utils.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ExecutionPanel implements Initializable {
    //region Fields
    @FXML
    private ListView<Integer> programsListView;
    @FXML
    private ListView<Statement> executionStackListView;
    @FXML
    private TableView<Pair> symbolsTableView;
    @FXML
    private TableColumn<Pair, String> symbolsTableViewId;
    @FXML
    private TableColumn<Pair, String> symbolsTableViewName;
    @FXML
    private TableView<Pair> heapTableView;
    @FXML
    private TableColumn<Pair, String> heapTableViewAddress;
    @FXML
    private TableColumn<Pair, String> heapTableViewName;
    @FXML
    private TableView<Pair> fileTableView;
    @FXML
    private TableColumn<Pair, String> fileTableViewId;
    @FXML
    private TableColumn<Pair, String> fileTableViewName;
    @FXML
    private TableColumn<Pair, String> fileTableViewBuffer;
    @FXML
    private ListView<Integer> outputListView;
    @FXML
    private Button oneStepButton;

    private Stage stage;
    private Statement originalProgram;
    private Controller controller;
    private ProgramState selectedProgramState;
    private Heap heap;
    private FileTable fileTable;
    private Output output;
    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //region Properties
    public void createProgramState(){
        ExecutionStack<Statement> executionStack = new InterpreterExecutionStack<>();
        SymbolsTable<String, Integer> symbolsTable = new InterpreterSymbolsTable<>();
        this.output = new InterpreterOutput();
        this.fileTable = new InterpreterFileTable();
        this.heap = new InterpreterHeap();
        this.selectedProgramState = new ProgramState(this.originalProgram, executionStack, symbolsTable, this.output, this.fileTable, this.heap);
        Repository repository = new InterpreterRepository("gui.txt");
        repository.addProgramState(this.selectedProgramState);
        this.controller = new Controller(repository);
    }

    public void setOriginalProgram(Statement originalProgram){
        this.originalProgram = originalProgram;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private List<Integer> getIdsOfPrograms(List<ProgramState> programStates){
        return programStates.stream().map(p -> p.getId()).collect(Collectors.toList());
    }

    public void populate(){
        populateProgramsList();
        populateExecutionStack();
        populateSymbolsTable();
        populateOutputList();
        populateFileTable();
        populateHeap();
    }
    //endregion

    //region Methods
    private void populateProgramsList() {
        List<Integer> programs = new ArrayList<>();
        programs.addAll(this.getIdsOfPrograms(controller.getPrograms()));
        ObservableList<Integer> programsObservable = FXCollections.observableArrayList(programs);
        programsListView.setItems(programsObservable);
    }

    private void populateExecutionStack(){
        List<Statement> executionStackList = new ArrayList<>();
        for(Statement stmt: selectedProgramState.getExecutionStack().getAll()){
            executionStackList.add(stmt);
        }
        ObservableList<Statement> executionStackObservableList = FXCollections.observableArrayList(executionStackList);
        executionStackListView.setItems(executionStackObservableList);
    }

    private void populateSymbolsTable(){
        List<Pair> symbolsTableList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: selectedProgramState.getSymbolsTable().getAll()){
            symbolsTableList.add(new Pair(entry.getKey(), entry.getValue()));
        }
        ObservableList<Pair> symbolsTableObservableList = FXCollections.observableArrayList(symbolsTableList);
        symbolsTableView.setItems(symbolsTableObservableList);
    }

    private void populateHeap(){
        List<Pair> heapList = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: selectedProgramState.getHeap().getAll()){
            heapList.add(new Pair(entry.getKey(), entry.getValue()));
        }
        ObservableList<Pair> heapObservableList = FXCollections.observableArrayList(heapList);
        heapTableView.setItems(heapObservableList);
    }

    private void populateFileTable(){
        List<Pair> fileTableList = new ArrayList<>();
        for(Map.Entry<Integer, FileData> entry: selectedProgramState.getFileTable().getAllFiles()){
            fileTableList.add(new Pair(entry.getKey(), entry.getValue()));
        }
        ObservableList<Pair> fileTableObservableList = FXCollections.observableArrayList(fileTableList);
        fileTableView.setItems(fileTableObservableList);
    }

    private void populateOutputList(){
        List<Integer> outputList = new ArrayList<>();
        for(Integer i: this.selectedProgramState.getOutput().getAll()){
            outputList.add(i);
        }
        ObservableList<Integer> outputObservableList = FXCollections.observableArrayList(outputList);
        outputListView.setItems(outputObservableList);
    }

    public void configure(){
        configureExecutionStack();
        configureSymbolsTable();
        configureHeap();
        configureFileTable();
        configureExecutionOneStepButton();
    }

    private void configureExecutionStack() {
        programsListView.setCellFactory(param -> {
            ListCell<Integer> cell = new ListCell<Integer>() {

                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null) {
                        setText(item.toString());
                    }
                }
            };

            return cell;
        });

        programsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected id: " + newValue);
            if (newValue != null) {
                System.out.println("Selected new state: " + controller.getProgramsById(newValue));
                this.selectedProgramState = controller.getProgramsById(newValue);
                populate();
            }
        });
    }

    private void configureSymbolsTable() {
        symbolsTableViewId.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().toString()));
        symbolsTableViewName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().toString()));
    }

    private void configureHeap() {
        heapTableViewAddress.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().toString()));
        heapTableViewName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().toString()));
    }

    private void configureFileTable() {
        fileTableViewId.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().toString()));
        fileTableViewName.setCellValueFactory(param -> {
            FileData fd = (FileData) param.getValue().getValue();
            return new SimpleStringProperty(fd.getFileName());
        });
        fileTableViewBuffer.setCellValueFactory(param -> {
            FileData fd = (FileData) param.getValue().getValue();
            return new SimpleStringProperty(fd.getReader().toString());
        });
    }

    private void configureExecutionOneStepButton(){
        oneStepButton.setOnAction(event -> {
            try {
                controller.allStepGUI();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            populate();
            System.out.print(controller.removeCompleteProgram(controller.getPrograms()));
            if(controller.removeCompleteProgram(controller.getPrograms()).isEmpty()){
                popFinishedExecutionWindow(stage);
            }
        });
    }

    private void popFinishedExecutionWindow(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to select another program?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
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
        if(alert.getResult() == ButtonType.NO){
            stage.hide();
        }
        if(alert.getResult() == ButtonType.CANCEL){
            alert.hide();
        }
    }
    //endregion
}