package com.golem.fileCell.fileSystem;

public final class Colorist {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static final String BLACK (String message) {
        return ANSI_BLACK + message + ANSI_RESET;
    }
    public static final String RED (String message) {
        return ANSI_RED + message + ANSI_RESET;
    }
    public static final String GREEN (String message) {
        return ANSI_GREEN + message + ANSI_RESET;
    }
    public static final String YELLOW (String message) {
        return ANSI_YELLOW + message + ANSI_RESET;
    }
    public static final String BLUE (String message) {
        return ANSI_BLUE + message + ANSI_RESET;
    }
    public static final String PURPLE (String message) {
        return ANSI_PURPLE + message + ANSI_RESET;
    }
    public static final String CYAN (String message) {
        return ANSI_CYAN + message + ANSI_RESET;
    }
    public static final String WHITE (String message) {
        return ANSI_WHITE + message + ANSI_RESET;
    }
}
