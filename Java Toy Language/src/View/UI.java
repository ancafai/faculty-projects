//package View;
//
//import Controller.Controller;
//import Model.*;
//import Utils.*;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class UI {
//    //region Fields
//    public Controller controller;
//    //endregion
//    //region Constructor
//    public UI(Controller controller){
//        this.controller = controller;
//    }
//    //endregion
//    //region Methods
//    public void printMenu(String[] menuOption){
//        StringBuilder builder = new StringBuilder();
//        for (String s: menuOption) {
//            builder.append(s);
//        }
//        System.out.print(builder.toString());
//    }
//
//    public Expression expressionBuilder(){
//        String[] expressionOptions = { "Please select a type of expression: \n",
//                "1. Arithmetic Expression;\n",
//                "2. Variable Expression;\n",
//                "3. Constant Expression;\n",
//                "0. Back\n"};
//        printMenu(expressionOptions);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        Expression mainExpression = null;
//        boolean done = false;
//        while(!done){
//            int expressionOption = -1;
//            try{
//                System.out.print("Select expression option: ");
//                expressionOption = Integer.parseInt(reader.readLine());
//            }catch(Exception e){
//                System.out.print("Invalid Option\n");
//            }
//            switch (expressionOption){
//                case 1:
//                    Expression firstExpression = expressionBuilder();
//                    Expression secondExpression = expressionBuilder();
//                    char operator = ' ';
//                    while(operator != '+' && operator != '-' && operator != '*' && operator != '/') {
//                        System.out.print("Input operator(" + "+" + ";-" + ";*" + ";/)" + ": ");
//                        try {
//                            operator = reader.readLine().charAt(0);
//                            mainExpression = new ArithmeticExpression(firstExpression, secondExpression, operator);
//                        } catch (Exception e) {
//                            System.out.print("Invalid operator\n");
//                        }
//                    }
//                    done = true;
//                    break;
//                case 2:
//                    String name = null;
//                    while(name == null){
//                        System.out.print("Input name of variable: ");
//                        try {
//                            name = reader.readLine();
//                            mainExpression = new VariableExpression(name);
//                        } catch (Exception e) {
//                            System.out.print(e.toString()+"\n");
//                        }
//                    }
//                    done = true;
//                    break;
//                case 3:
//                    int value = -1;
//                    while(value == -1){
//                        System.out.print("Input a value: ");
//                        try{
//                            value = Integer.parseInt(reader.readLine());
//                            mainExpression = new ConstantExpression(value);
//                        }catch (Exception e){
//                            System.out.print(e + "\n");
//                        }
//                    }
//                    done = true;
//                    break;
//                case 0:
//                    done = true;
//                    break;
//                default:
//                    printMenu(expressionOptions);
//                    break;
//            }
//        }
//        return mainExpression;
//    }
//
//    public Statement statementBuilder(){
//        String[] statementOptions = { "Please select a type of statement: \n",
//                "1. Compound Statement;\n",
//                "2. Assign Statement;\n",
//                "3. Print Statement;\n",
//                "4. If Statement;\n",
//                "0. Back\n"};
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        Statement mainStatement = null;
//        boolean done = false;
//        while(!done) {
//            printMenu(statementOptions);
//            int statementOption = -1;
//            try {
//                System.out.print("Select statement option: ");
//                statementOption = Integer.parseInt(reader.readLine());
//            }catch(Exception e){
//                System.out.print("Invalid option \n");
//            }
//            switch (statementOption) {
//                case 1:
//                    Statement statement1 = statementBuilder();
//                    Statement statement2 = statementBuilder();
//                    mainStatement = new CompoundStatement(statement1, statement2);
//                    done = true;
//                    break;
//                case 2:
//                    String name = null;
//                    Expression assignExpression = null;
//                    while(name == null) {
//                        System.out.print("Input name of variable: ");
//                        try {
//                            name = reader.readLine();
//                            assignExpression = expressionBuilder();
//                            if(assignExpression == null)
//                                continue;
//                            mainStatement = new AssignStatement(name, assignExpression);
//                            done = true;
//                        } catch (Exception e) {
//                            System.out.print(e + "\n");
//                        }
//                    }
//                    break;
//                case 3:
//                    Expression printExpression = expressionBuilder();
//                    mainStatement = new PrintStatement(printExpression);
//                    if(printExpression != null)
//                        done = true;
//                    break;
//                case 4:
//                    Statement firstStatement = statementBuilder();
//                    Statement secondStatement = statementBuilder();
//                    Expression ifExpression = expressionBuilder();
//                    mainStatement = new IfStatement(ifExpression, firstStatement, secondStatement);
//                    if(mainStatement != null)
//                        done = true;
//                    break;
//                case 0:
//                    done = true;
//                    break;
//                default:
//                    printMenu(statementOptions);
//                    break;
//            }
//        }
//        return mainStatement;
//    }
//
//    public void menu(){
//        String[] menuOptions = {"Toy Language options: \n",
//                "1. Add a Program;\n",
//                "2. Step by step execution of the latest program;\n",
//                "3. Execution of all the program;\n",
//                "0. Exit\n"};
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        boolean done = false;
//        while(!done) {
//            printMenu(menuOptions);
//            int option = -1;
//            try {
//                System.out.print("Select option: ");
//                option = Integer.parseInt(reader.readLine());
//            }catch (Exception a){
//                System.out.print("Invalid option \n");
//            }
//            switch (option) {
//                case 1:
//                    ExecutionStack<Statement> executionStack= new InterpreterExecutionStack<Statement>();
//                    SymbolsTable<String, Integer> symbolsTable = new InterpretorSymbolsTable<String, Integer>();
//                    Output<Integer> output = new InterpreterOutput<Integer>();
//                    ProgramState programState;
//                    Statement initialProgramStatement = statementBuilder();
//                    if(initialProgramStatement != null) {
//                        programState = new ProgramState(initialProgramStatement, executionStack, symbolsTable, output);
//                        controller.addProgram(programState);
//                        System.out.print("Program successfully added!!!\n");
//                    }
//                    else{
//                        System.out.print("Failed!\n");
//                    }
//                    break;
//                case 2:
//                    try {
//                        controller.executeOneStep(controller.getCurrentProgramState());
//                    }
//                    catch(Exception e){
//                        System.out.print(e);
//                    }
//                    break;
//                case 3:
//                    try{
//                        controller.executeAllSteps();
//                    }
//                    catch (Exception e){
//                        System.out.print(e);
//                    }
//                    break;
//                case 0:
//                    done = true;
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//    //endregion
//}