package com.golem.scriptCell;

import com.golem.core.schemas.basicAbstractions.AbstractCommand;
import com.golem.core.schemas.basicInterfaces.BroodMotherCell;
import com.golem.core.schemas.providedRealisations.CellPrinter;
import com.golem.fileCell.fileReader.SimpleReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ScriptCommandCell extends AbstractCommand {
    private List<String> signature;
    private final List<String> openedFiles = new ArrayList<>();
    private boolean recursion = false;
    private List <List<String>> commandsQueue = new ArrayList<>();
    private List <String> rowQueue = new ArrayList<>();
    private BroodMotherCell broodMother;
    public void addBroodMother (BroodMotherCell broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public void activate() {
        rowQueue.addAll(fileLoad(signature.get(0).split(" ")[1]));
        List<String> temp = new ArrayList<>();
        boolean repeat;
        while (!recursion) {
            repeat = false;
            for (String s : rowQueue) {
                if (s.matches("execute_script .+\\.txt")) {
                    temp.addAll(fileLoad(s.split(" ")[1]));
                    repeat = true;
                }
                else {
                    temp.add(s);
                }
            }
            rowQueue = temp;
            temp = new ArrayList<>();
            if (!repeat) break;
        }
        commandsQueue = formArgs(rowQueue);
        commandsQueue = checkable(commandsQueue);
        setAnswer(executable(commandsQueue));
    }

    @Override
    public AbstractCommand useSignature(List<String> signature) {
        this.signature = signature;
        return this;
    }

    private List<String> fileLoad (String fileName) {
        if (openedFiles.stream().anyMatch(x -> x.equals(fileName))){
            recursion = true;
            return new ArrayList<>();
        }
        try {
            openedFiles.add(fileName);
            return SimpleReader.filerRead(fileName);
        } catch (IOException e) {
            CellPrinter.setMessage(e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<List<String>> formArgs (List<String> rowQueue) {
        List<List<String>> result = new ArrayList<>();
        List<String> args = new ArrayList<>();
        int counter = 0;
        for (String s : rowQueue) {
            if (counter > 0) {
                args.add(s);
                counter--;
                continue;
            }
            if (broodMother.getFactoryCommands().get(s) != null) {
                if (!args.isEmpty()) {
                    result.add(new ArrayList<>(args));
                    args = new ArrayList<>();
                }
                args.add(s);
                counter += broodMother.getFactoryCommands().get(args.get(0))
                        .getSignature()
                        .patternSignature()
                        .size() - 1;
            }
            else {
                args.add(s);
            }
        }
        result.add(args);
        return result;
    }

    private List<List<String>> checkable (List<List<String>> queue) {
        List<List<String>> result = new ArrayList<>();
        List<String> signature;
        boolean corrupt;
        for (List<String> ls : queue) {
            corrupt = false;
            signature = broodMother.getFactoryCommands().get(ls.get(0)).getSignature().patternSignature();
            if (signature.size() != ls.size()) {
                result.add(new ArrayList<>());
                continue;
            }
            for (int i = 0; i < signature.size(); i++) {
                if (!Pattern.matches(signature.get(i), ls.get(i))) {
                    corrupt = true;
                }
            }
            if (!corrupt) {
                result.add(ls);
            }
            else {
                result.add(new ArrayList<>());
            }
        }
        return result;
    }

    private List<String> executable (List<List<String>> queue) {
        List<String> answer = new ArrayList<>();
        AbstractCommand command;
        for (List<String> ls : queue) {
            if (!ls.isEmpty()) {
                command = broodMother.createCell(ls.get(0).split(" ")[0], ls, getLogin());
                command.activate();
                answer.add(unwrapList(command.getAnswer()));
            }
            else {
                answer.add("Command data was corrupted or produce infinite cycle");
            }
        }
        return answer;
    }

    private String unwrapList (List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append("\n");
        }
        return result.toString();
    }
}
