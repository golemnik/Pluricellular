package com.golem.core.coreCell;

import com.golem.core.schemas.basicRealisation.BasicBroodMotherCell;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.schemas.providedRealisations.TerminalComparator;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.core.schemas.basicAbstractions.AbstractExtendedICellCore;
import com.golem.core.schemas.basicAbstractions.AbstractQueenCell;
import com.golem.core.schemas.basicAbstractions.AbstractTerminal;

public class CoreCell extends AbstractExtendedICellCore implements Cell {
    private AbstractTerminal terminal;

    public AbstractTerminal getTerminal() {
        return terminal;
    }

    public void addTerminal(AbstractTerminal terminal) {
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

        getBroodMother().setAll(this, getBroodMother(), getQueenConnections());

        getQueenConnections().forEach(queenCell -> queenCell.setAll(this, getBroodMother(), queenCell));
        getQueenConnections().forEach(queenCell -> queenCell.addLayer(CellLayer.getLayer()));
        getQueenConnections().forEach(queenCell -> getBroodMother().addFactoryList(queenCell.extractFactories(queenCell.getLayer()), queenCell));
        addTerminal(AbstractTerminal.getCellTerminals(CellLayer.getLayer()).stream().sorted(new TerminalComparator()).toList().get(0));

        terminal.setAll(this, getBroodMother(), getQueenConnections());
        terminal.activate();
    }
}

// \terminal commands