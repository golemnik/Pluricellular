package com.golem.core.basicRealisation;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.innerMechanisms.LoadCells;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.TerminalCell;

import java.util.Scanner;

public class BasicTerminalCell implements TerminalCell {
    private CellBroodMother broodMother;
    private CoreCell coreCell;
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
    public void terminalInit() {
        System.out.println("Basic terminal in use...\n");
    }

    @Override
    public void terminalCycle() {
        while (true) {
            broodMother.reloadFactoryList(LoadCells.loadFactories(CellLayer.getLayer()));
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
