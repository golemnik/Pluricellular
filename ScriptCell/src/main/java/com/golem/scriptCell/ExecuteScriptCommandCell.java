package com.golem.scriptCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.fileCell.fileReader.SimpleReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ExecuteScriptCommandCell extends AbstractCommand implements Cell {
    private static final List<String> openedFiles = new ArrayList<>();
    private static boolean globalFail = false;
    private boolean skip = true;
    private String currentFile;
    private List <String> commandsQueue;
    private BroodMotherCell broodMother;

    public void addBroodMother (BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }
    @Override
    public void activate() {
        try {
            List<String > dataList;
            AbstractCommand cellCommand;
            int counter = commandsQueue.size();
            if (!commandsQueue.isEmpty() && !skip && !globalFail) {
                for (int i = 0; i < counter; i++) {
//                    System.out.println(">>" + commandsQueue + " - "+ commandsQueue.size());
                    String command = commandsQueue.get(0);
                    if (broodMother.getFactoryCommands().keySet().stream().anyMatch(x -> x.equals(command))) {
                        dataList = listExchange(commandsQueue, broodMother.getFactoryCommands().get(command).getSignature().patternSignature().size());
                        dataList = listCheck(dataList, broodMother.getFactoryCommands().get(command).getSignature().patternSignature());
                        if (dataList != null) {
                            cellCommand = broodMother.createCell(command, dataList);
                            cellCommand.activate();
                            CellPrinter.setMessage(cellCommand.getAnswer().toString());
                        }
                    }
                    else {
                        globalFail = true;
                        clearLastScr();
                        setAnswer(List.of("Err"));
                        return;
                    }
//                    System.out.println("<<" + commandsQueue + " - "+ commandsQueue.size());
                }
            }
            clearLastScr();
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage() + " " + e.getCause());
        }
        setAnswer(List.of("Success"));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        currentFile = signature.get(0).split(" ")[1];
        if (openedFiles.stream().anyMatch(x -> x.equals(currentFile))){
            return this;
        }
        try {
            openedFiles.add(currentFile);
            skip = false;
            commandsQueue = SimpleReader.filerRead(currentFile);
        } catch (IOException e) {
            CellPrinter.setMessage(e.getMessage());
        }
        return this;
    }

    private List <String> listExchange (List <String> listFrom, int amount) {
        List <String> listTo = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            listTo.add(listFrom.get(i));
            listFrom.remove(i);
        }
        return listTo;
    }

    private List <String> listCheck (List <String> list, List<String> signature) {
        for (int i = 0; i < signature.size(); i++) {
            if (!Pattern.matches(signature.get(i), list.get(i))) {
                return null;
            }
        }
        return list;
    }

    private void clearLastScr () {
        if (openedFiles.size() == 1) {
            globalFail = false;
        }
        if (openedFiles.size() > 0) {
            openedFiles.remove(openedFiles.size()-1);
        }
    }

}

//              execute_script sc.txt