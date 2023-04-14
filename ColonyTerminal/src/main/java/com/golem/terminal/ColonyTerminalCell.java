package com.golem.terminal;

import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.TerminalCell;
import com.golem.terminal.terminalCommands.TerminalCommandsCell;
import com.golem.terminal.terminalCommands.schemes.TerminalCommandFactory;

import java.util.Scanner;

public class ColonyTerminalCell implements TerminalCell {
    private CellBroodMother broodMother;
    private CoreCell coreCell;
    private BroodQueen queen;
    private Scanner scanner;
    private TerminalCommandsCell terminalCommandsCell;

    public ColonyTerminalCell () {
        terminalCommandsCell = new TerminalCommandsCell();
    }

    @Override
    public void activate() {
        terminalCommandsCell.LoadCommands(TerminalCommandFactory.getCommandsFactories(coreCell.getBroodQueen().getLayer()));
        terminalCommandsCell.activate();
        terminalInit();
        System.out.println(terminalCommandsCell.getCommandList());
        terminalCycle();
    }

    @Override
    public void terminalInit() {
        System.out.println("Colony terminal in use...");
    }

    @Override
    public void terminalCycle() {
        scanner = new Scanner(System.in);
        while (true) {
            coreCell.updateModuleLayer();
            coreCell.getBroodQueen().updateLayer();
            broodMother.reloadFactoryList(BroodQueen.loadAbsFactories(coreCell.getBroodQueen().getLayer()));
            System.out.println(broodMother.getFactoryCommands());
            broodMother.createCell(scanner.nextLine()).activate();
        }
    }

    @Override
    public void addBroodMother(CellBroodMother broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public CellBroodMother getBroodMother() {
        return null;
    }

    @Override
    public void addCore(CoreCell coreCell) {
        this.coreCell = coreCell;
    }

    @Override
    public CoreCell getCoreCell() {
        return null;
    }


    @Override
    public void setAll(CoreCell coreCell, CellBroodMother broodMother, BroodQueen broodQueen) {

    }

    @Override
    public void addBroodQueen(BroodQueen broodQueen) {

    }

    @Override
    public BroodQueen getBroodQueen() {
        return null;
    }
}
