package com.golem.fileCell.fileSystem;

import java.util.Scanner;

public class ConsoleInputer implements Input {
    Scanner scanner;
    public ConsoleInputer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean script() {
        return true;
    }

    @Override
    public String input() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        else {
            System.exit(0);
            return "";
        }
    }
}
