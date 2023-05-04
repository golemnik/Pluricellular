package com.golem.core.basicRealisation;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.TerminalCell;
import com.golem.core.schemas.abstracts.AbstractICellCore;

import java.util.Scanner;

public class BasicTerminalCell extends AbstractICellCore implements TerminalCell {
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
            input = scanner.nextLine();
            if (input.equals("\\terminal commands")) {
                System.out.println(getBroodMother().getFactoryCommands().keySet());
                continue;
            }
            if (input.equals("\\terminal layer")) {
                System.out.println(CellLayer.getLayer().modules());
                continue;
            }
            try {
                getBroodMother().createCell(input).activate();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void activate() {
        terminalInit();
        terminalCycle();
    }
}
