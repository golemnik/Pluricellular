package com.golem.fileCell.fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleReader {

    public static List<String> filerRead(String fileName) throws FileNotFoundException {
        if (!findFile(fileName)) return null;
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        if (!list.isEmpty()) return list;
        return null;
    }

    private static boolean findFile (String fileName) {
        File file = new File(fileName);
        return file.isFile();
    }

}

//execute_script sc.txt