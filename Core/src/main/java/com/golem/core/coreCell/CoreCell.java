package com.golem.core.coreCell;


import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.TerminalCell;
import com.golem.core.schemas.abstracts.AbstractInnerCellFullCore;

public class CoreCell extends AbstractInnerCellFullCore implements Cell {
    private TerminalCell terminal;

    public TerminalCell getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalCell terminal) {
        this.terminal = terminal;
    }

    public CoreCell () {
        addCore(this);
        addBroodQueen(new BroodQueen());
        CellLayer.setPath("genome");
    }

    public void updateModuleLayer() {
        CellLayer.reloadLayer();
    }
    @Override
    public void activate() {
        updateModuleLayer();
        getBroodQueen().addLayer(CellLayer.getLayer());
        System.out.println("Loading brood...");
        addBroodMother(getBroodQueen().createBaseCell(BroodQueen.loadBroodMothers(CellLayer.getLayer())));
        System.out.println("Producing BroodMother dependencies...");
        getBroodMother().setAll(this, getBroodMother(), getBroodQueen());
        System.out.println("Loading cell factories...");
        getBroodMother().addFactoryList(BroodQueen.loadAbsFactories(CellLayer.getLayer()));
        System.out.println("Loading terminal...");
        setTerminal(getBroodQueen().createBaseCell(BroodQueen.loadTerminals(CellLayer.getLayer())));
        System.out.println("Producing terminal dependencies...");
        terminal.setAll(this, getBroodMother(), getBroodQueen());
        while (true) {
            terminal.activate();
        }
    }
}
