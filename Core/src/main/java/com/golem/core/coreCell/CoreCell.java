package com.golem.core.coreCell;


import com.golem.core.basicRealisation.BasicBroodMotherCell;
import com.golem.core.queens.BroodQueen;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.TerminalCell;
import com.golem.core.schemas.abstracts.AbstractExtendedICellCore;
import com.golem.core.schemas.abstracts.AbstractQueenCell;

public class CoreCell extends AbstractExtendedICellCore implements Cell {
    private TerminalCell terminal;

    public TerminalCell getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalCell terminal) {
        this.terminal = terminal;
    }

    public CoreCell () {
        addCore(this);
        addBroodMother(new BasicBroodMotherCell());
        CellLayer.setPath("genome");
    }

    @Override
    public void activate() {
        CellLayer.reloadLayer();
        addQueenConnection(AbstractQueenCell.getQueens(CellLayer.getLayer()));
        getQueenConnections().forEach(queenCell -> queenCell.setAll(this, getBroodMother(), queenCell));
        getQueenConnections().forEach(queenCell -> queenCell.addLayer(CellLayer.getLayer()));
        getQueenConnections().forEach(queenCell -> queenCell.fillBroodMother(queenCell.getLayer()));


        System.out.println(getQueenConnections().size());
        for (AbstractQueenCell aqc : getQueenConnections()) {
            System.out.println(aqc.getClass().getSimpleName());
        }

        System.out.println(getCoreCell().getClass().getSimpleName());
        System.out.println(getBroodMother().getClass().getSimpleName());
//
//        CellPrinter.setMessage("Loading brood...");
//        addBroodMother(getBroodQueen().createBaseCell(BroodQueen.loadBroodMothers(CellLayer.getLayer())));
//        CellPrinter.setMessage("Producing BroodMother dependencies...");
//        getBroodMother().setAll(this, getBroodMother(), getBroodQueen());
//        CellPrinter.setMessage("Loading cell factories...");
//        getBroodMother().reloadFactoryList(BroodQueen.loadSystemFactories(CellLayer.getLayer()));
//        getBroodMother().addFactoryList(BroodQueen.loadOuterFactories(CellLayer.getLayer()));
//
//        CellPrinter.setMessage("Loading connected branch cells...");
//        ConnectorQueen connectorQueen = new ConnectorQueen();
//        connectorQueen.setAll(this, getBroodMother(), getBroodQueen());
//        CellPrinter.setMessage("Producing outer data dependencies & commands...");
//        connectorQueen.activate();
//
//        CellPrinter.setMessage("Loading terminal...");
//        setTerminal(getBroodQueen().createBaseCell(BroodQueen.loadTerminals(CellLayer.getLayer())));
//        CellPrinter.setMessage("Producing terminal dependencies...");
//
//
//
//        terminal.setAll(this, getBroodMother(), getBroodQueen());
//        while (true) {
//            terminal.activate();
//        }
    }
}

// \terminal commands