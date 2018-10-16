/*
package Main;

import View.ViewController;

        Statement divideBy0Exception = new PrintStatement(new ArithmeticExpression(new ConstantExpression(5), new ConstantExpression(0), '/'));
        Repository repositoryDivideBy0Exception = new InterpreterRepository("Divide By 0 Exception.txt");
        ProgramState programDivideBy0Exception = new ProgramState(divideBy0Exception, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositoryDivideBy0Exception.addProgramState(programDivideBy0Exception);
        Controller controllerDivideBy0Exception = new Controller(repositoryDivideBy0Exception);

        Statement file = new CompoundStatement(new CompoundStatement(new OpenFileStatement("test.txt", "fileVar"), new ReadFileStatement(new ConstantExpression(1), "a")), new CloseFileStatement(new ConstantExpression(1)));
        Repository repositoryFile = new InterpreterRepository("File.txt");
        ProgramState programFile = new ProgramState(file, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositoryFile.addProgramState(programFile);
        Controller controllerFile = new Controller(repositoryFile);

        Statement fileException = new ReadFileStatement(new ConstantExpression(1), "a");
        Repository repositoryFileException = new InterpreterRepository("FileException.txt");
        ProgramState programFileException = new ProgramState(fileException, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositoryFileException.addProgramState(programFileException);
        Controller controllerFileException = new Controller(repositoryFileException);

        Statement heapStatement = new CompoundStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new NewStatement("v", new ConstantExpression(20))), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new WriteHeapStatement("a", new ConstantExpression(30)))), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new ReadHeapExpression("a")))), new AssignStatement("a", new ConstantExpression(0)));
        Repository repositoryHeap = new InterpreterRepository("FileHeap.txt");
        ProgramState programHeap = new ProgramState(heapStatement, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositoryHeap.addProgramState(programHeap);
        Controller controllerHeap = new Controller(repositoryHeap);

        Statement whileStatement = new CompoundStatement(new CompoundStatement(new AssignStatement("v", new ConstantExpression(6)), new WhileStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(2), '-'), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), '-'))))), new PrintStatement(new VariableExpression("v")));
        Repository repositoryWhile = new InterpreterRepository("FileWhile.txt");
        ProgramState programWhile = new ProgramState(whileStatement, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositoryWhile.addProgramState(programWhile);
        Controller controllerWhile = new Controller(repositoryWhile);

        Statement serializableStatement = new AssignStatement("v", new ConstantExpression(2));
        Repository repositorySerializable = new InterpreterRepository("FileSerializable.txt");
        ProgramState programSerializable = new ProgramState(serializableStatement, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositorySerializable.addProgramState(programSerializable);
        Controller controllerSerializable = new Controller(repositorySerializable);

        Statement threadStatement = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)), new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a")))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a"))))));
        Repository repositoryThread = new InterpreterRepository("FileThread.txt");
        ProgramState programThread = new ProgramState(threadStatement, new InterpreterExecutionStack<>(), new InterpreterSymbolsTable<>(), new InterpreterOutput<>(), new InterpreterFileTable<>(), new InterpreterHeap<>());
        repositoryThread.addProgramState(programThread);
        Controller controllerThread = new Controller(repositoryThread);

        TextMenu ui = new TextMenu();
        ui.addCommand(new AllStepsCommand("1", workingInMemory.toString(), controllerMemory));
        ui.addCommand(new AllStepsCommand("2", variableException.toString(), controllerVariableException));
        ui.addCommand(new AllStepsCommand("3", divideBy0Exception.toString(), controllerDivideBy0Exception));
        ui.addCommand(new AllStepsCommand("4", file.toString(), controllerFile));
        ui.addCommand(new AllStepsCommand("5", fileException.toString(), controllerFileException));
        ui.addCommand(new AllStepsCommand("6", heapStatement.toString(), controllerHeap));
        ui.addCommand(new AllStepsCommand("7", whileStatement.toString(), controllerWhile));
        ui.addCommand(new DeserializeCommand("8", "Deserialize into a new program", controllerSerializable));
        ui.addCommand(new SerializeCommand("9", "Serialize: " + serializableStatement, controllerSerializable));
        ui.addCommand(new AllStepsCommand("10", "Execute Deserialized Program", controllerSerializable));
        ui.addCommand(new AllStepsForAllProgramsCommand("t", threadStatement.toString(), controllerThread));
        ui.addCommand(new ExitCommand("0", "Exit"));

        ui.show();
    }
}
*/
