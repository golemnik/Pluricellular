package com.golem.core.coreCell;

import com.golem.core.basicRealisation.BasicBroodMotherCell;
import com.golem.core.innerMechanisms.CellLayer;
import com.golem.core.innerMechanisms.TerminalComparator;
import com.golem.core.schemas.Cell;
import com.golem.core.schemas.abstracts.AbstractExtendedICellCore;
import com.golem.core.schemas.abstracts.AbstractQueenCell;
import com.golem.core.schemas.abstracts.AbstractTerminal;

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
        CellLayer.setPath("genome/genome");
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

        System.out.println(CellLayer.getLayer().modules());
        for (AbstractQueenCell aqc : getQueenConnections()) {
            System.out.println(aqc.getClass().getSimpleName());
        }
        System.out.println(getBroodMother().getClass().getSimpleName());
        System.out.println(getTerminal().getClass().getSimpleName());
        System.out.println("=---=");
        System.out.println(getBroodMother().getFactoryCommands().keySet());

        terminal.setAll(this, getBroodMother(), getQueenConnections());
        while (true) {
            terminal.activate();
        }
    }
}

// \terminal commands