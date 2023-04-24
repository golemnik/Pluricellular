package com.golem.core.coreCell;


import com.golem.core.broodQueen.BroodQueen;
import com.golem.core.connectorCells.ConnectorQueen;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.TerminalCell;
import com.golem.core.schemas.abstracts.AbstractInnerCellFullCore;
import com.golem.core.schemas.providedRealisations.CellPrinter;

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

        CellPrinter.setMessage("Loading brood...");
        addBroodMother(getBroodQueen().createBaseCell(BroodQueen.loadBroodMothers(CellLayer.getLayer())));
        CellPrinter.setMessage("Producing BroodMother dependencies...");
        getBroodMother().setAll(this, getBroodMother(), getBroodQueen());
        CellPrinter.setMessage("Loading cell factories...");
        getBroodMother().reloadFactoryList(BroodQueen.loadSystemFactories(CellLayer.getLayer()));
        getBroodMother().addFactoryList(BroodQueen.loadOuterFactories(CellLayer.getLayer()));

        CellPrinter.setMessage("Loading connected branch cells...");
        ConnectorQueen connectorQueen = new ConnectorQueen();
        connectorQueen.setAll(this, getBroodMother(), getBroodQueen());
        CellPrinter.setMessage("Producing outer data dependencies & commands...");
        connectorQueen.activate();

        CellPrinter.setMessage("Loading terminal...");
        setTerminal(getBroodQueen().createBaseCell(BroodQueen.loadTerminals(CellLayer.getLayer())));
        CellPrinter.setMessage("Producing terminal dependencies...");



        terminal.setAll(this, getBroodMother(), getBroodQueen());
        while (true) {
            terminal.activate();
        }
    }
}

// \terminal commands