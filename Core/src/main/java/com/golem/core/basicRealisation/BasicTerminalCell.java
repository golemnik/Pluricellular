package com.golem.core.basicRealisation;
import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.TerminalCell;
import com.golem.core.schemas.abstracts.AbstractInnerCellFullCore;

import java.util.Scanner;

public class BasicTerminalCell extends AbstractInnerCellFullCore implements TerminalCell {
    private final Scanner scanner;
    public BasicTerminalCell () {
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void terminalInit() {
        System.out.println("Basic terminal in use...\n");
    }

    @Override
    public void terminalCycle() {
        String input;
        while (true) {
//            getCoreCell().updateModuleLayer();
//            queen.activate();
//            getBroodMother().reloadFactoryList(BroodQueen.loadAbsFactories(getBroodQueen().getLayer()));
            input = scanner.nextLine();
            if (input.equals("\\terminal commands")) {
                System.out.println(getBroodMother().getFactoryCommands().keySet());
                continue;
            }
            Cell cell = getBroodMother().createCell(input);
            cell.activate();
        }
    }

    @Override
    public void activate() {
        terminalInit();
        terminalCycle();
    }
}
