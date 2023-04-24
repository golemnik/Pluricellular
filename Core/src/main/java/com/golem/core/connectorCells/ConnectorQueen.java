package com.golem.core.connectorCells;

import com.golem.core.schemas.Cell;
import com.golem.core.schemas.ConnectedCell;
import com.golem.core.schemas.abstracts.AbstractInnerCellFullCore;

import java.util.List;

public class ConnectorQueen extends AbstractInnerCellFullCore implements Cell {
    private List<ConnectedCell> connectedCells;
    public void loadConnectedCells () {
        connectedCells = ConnectedCell.getBranchConnectionCell(getBroodQueen().getLayer());
    }
    public void loadBranchCommands () {
        for (ConnectedCell cbm : connectedCells) {
            cbm.setAll(getCoreCell(), getBroodMother(), getBroodQueen());
            cbm.activate();
        }
    }

    @Override
    public void activate() {
        loadConnectedCells();
        loadBranchCommands();
    }
}
