package com.golem.terminal;

import com.golem.core.coreCell.CoreCell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.TerminalCell;
import com.golem.terminal.terminalCommands.TerminalCommandsCell;
import com.golem.terminal.terminalCommands.commands.TerminalCommandFactory;

public class ColonyTerminalCell implements TerminalCell {
    private CellBroodMother broodMother;
    private CoreCell coreCell;
    private TerminalCommandsCell terminalCommandsCell;

    public ColonyTerminalCell () {
        terminalCommandsCell = new TerminalCommandsCell();
    }

    @Override
    public void activate() {
        terminalCommandsCell.LoadCommands(TerminalCommandFactory.getCommandsFactories(coreCell.getQueen().getLayer()));
        terminalCommandsCell.activate();
        terminalInit();
        terminalCycle();
    }

    @Override
    public void terminalInit() {
        System.out.println("Colony terminal in use...");
    }

    @Override
    public void terminalCycle() {
        while (true) {

        }
    }

    @Override
    public void addBroodMother(CellBroodMother broodMother) {
        this.broodMother = broodMother;
    }

    @Override
    public void addCore(CoreCell coreCell) {
        this.coreCell = coreCell;
    }
}
