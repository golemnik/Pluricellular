package com.golem.core.basicRealisation;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.TerminalCell;

import java.util.Scanner;

public class BasicTerminalCell implements TerminalCell {
    private CellBroodMother broodMother;
    private CoreCell coreCell;
    private BroodQueen queen;
    private Scanner scanner;
    public BasicTerminalCell () {
        this.broodMother = new BasicBroodMother();
        this.scanner = new Scanner(System.in);
    }
    public void addBroodMother (CellBroodMother broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public void addCore(CoreCell coreCell) {
        this.coreCell = coreCell;
    }

    @Override
    public void addQueen(BroodQueen broodQueen) {
        this.queen = broodQueen;
    }


    @Override
    public void terminalInit() {
        System.out.println("Basic terminal in use...\n");
    }

    @Override
    public void terminalCycle() {
        while (true) {
            coreCell.updateModuleLayer();
//            queen.activate();
            broodMother.reloadFactoryList(BroodQueen.loadAbsFactories(queen.getLayer()));
            System.out.println(broodMother.getFactoryCommands());
            broodMother.createCell(scanner.nextLine()).activate();
        }
    }

    @Override
    public void activate() {
        terminalInit();
        terminalCycle();
    }
}
