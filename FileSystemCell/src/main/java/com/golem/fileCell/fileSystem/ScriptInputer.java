package com.golem.fileCell.fileSystem;

import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */

public class ScriptInputer implements Input{
    private List<String> inputLines = new ArrayList<>();
    private static List<String> tail = new ArrayList<>();
    public ScriptInputer (String file) {
        char sym;
        String line = "";
        int character;
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(file))) {
            while ((character = bif.read())!=-1) {
                sym = (char)character;
                if (sym == '\n') {
                    line = line.trim();
                    inputLines.add(line);
                    line = "";
                }
                else {
                    line += sym;
                }
            }
            if (!line.equals("")) {
                inputLines.add(line);
            }
            inputLines.addAll(tail);
            tail.clear();
            inputLines.add(null);
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
        }
    }

    public static void addTail (List <String> tail) {
        ScriptInputer.tail = tail;
        if (tail.size() > 0 && tail.get(tail.size()-1)==null) {
            tail.remove(tail.size()-1);
        }
    }

    @Override
    public boolean script() {
        return false;
    }

    @Override
    public String input() {
        String input;
        if (inputLines.size() > 0) {
            input = inputLines.get(0);
            inputLines.remove(0);
            return input;
        }
        return null;
    }
}
