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

    public void updateCoreLayer() {
        CellLayer.reloadLayer();
    }
    public void updateGenomeLayer() {
        CellLayer.reloadLayer();
    }

    @Override
    public void activate() {
        updateCoreLayer();
        updateGenomeLayer();
        getBroodQueen().addCoreLayer(CellLayer.getLayer());

        System.out.println("Loading brood...");
        addBroodMother(getBroodQueen().createBaseCell(BroodQueen.loadBroodMothers(CellLayer.getLayer())));
        System.out.println("Producing BroodMother dependencies...");
        getBroodMother().setAll(this, getBroodMother(), getBroodQueen());
        System.out.println("Loading cell factories...");
        getBroodMother().reloadFactoryList(BroodQueen.loadSystemFactories(CellLayer.getLayer()));
        getBroodMother().addFactoryList(BroodQueen.loadOuterFactories(CellLayer.getLayer()));
        System.out.println("Loading terminal...");
        setTerminal(getBroodQueen().createBaseCell(BroodQueen.loadTerminals(CellLayer.getLayer())));
        System.out.println("Producing terminal dependencies...");
        terminal.setAll(this, getBroodMother(), getBroodQueen());
        while (true) {
            terminal.activate();
        }
    }
}

// \terminal commands