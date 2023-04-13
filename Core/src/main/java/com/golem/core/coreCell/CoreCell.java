package com.golem.core.coreCell;


import com.golem.core.basicRealisation.BasicBroodMother;
import com.golem.core.basicRealisation.BasicTerminalCell;
import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.innerMechanisms.LoadCells;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.CellBroodMother;
import com.golem.core.schemas.TerminalCell;

public class CoreCell implements Cell {
    private BroodQueen queen;
    private CellBroodMother broodMother;
    private TerminalCell terminal;

    public BroodQueen getQueen() {
        return queen;
    }

    public CellBroodMother getBroodMother() {
        return broodMother;
    }

    public TerminalCell getTerminal() {
        return terminal;
    }

    private void initModuleLayer () {
        CellLayer.loadLayer("genome");
    }
    @Override
    public void activate() {
        initModuleLayer();
        queen = new BroodQueen();
        queen.addLayer(CellLayer.getLayer());
        System.out.println("Loading brood...");
        broodMother = queen.createBaseCell(LoadCells.loadBroodMothers(CellLayer.getLayer()));
        System.out.println("Loading cell factories...");
        broodMother.addFactoryList(LoadCells.loadFactories(CellLayer.getLayer()));
        System.out.println("Loading terminal...");
        terminal = queen.createBaseCell(LoadCells.loadTerminals(CellLayer.getLayer()));
        System.out.println("Connecting terminal to Core...");
        terminal.addCore(this);
        System.out.println("Connecting terminal to BroodMother...");
        terminal.addBroodMother(broodMother);
        System.out.println("Activating terminal cell...");
        while (true) {
            terminal.activate();
        }
    }
}
