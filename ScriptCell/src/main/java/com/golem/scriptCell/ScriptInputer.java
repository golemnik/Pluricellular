package com.golem.scriptCell;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScriptInputer{

    public static List<String> readScript (String file) {
        List<String> list = new ArrayList<>();
        char sym;
        String line = "";
        int character;
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(file))) {
            while ((character = bif.read())!=-1) {
                sym = (char)character;
                if (sym == '\n') {
                    line = line.trim();
                    list.add(line);
                    line = "";
                }
                else {
                    line += sym;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
