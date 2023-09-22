package com.golem.fileCell.fileReader;

import com.golem.core.schemas.providedRealisations.CellPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleReader {

    public static List<String> filerRead(String fileName) throws FileNotFoundException {
        if (!findFile(fileName)) return null;
        try {
            return Files.readAllLines(Path.of(fileName));
        }
        catch (Exception e) {
            CellPrinter.setMessage(e.getMessage());
            return new ArrayList<>();
        }
    }

    private static boolean findFile (String fileName) {
        File file = new File(fileName);
        return file.isFile();
    }

}

//execute_script sc.txt