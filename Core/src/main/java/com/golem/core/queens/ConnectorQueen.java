//package com.golem.core.queens;
//
//import com.golem.core.schemas.ConnectedCell;
//import com.golem.core.schemas.QueenCell;
//import com.golem.core.schemas.abstracts.AbstractICellCore;
//
//import java.util.List;
//
//public class ConnectorQueen extends AbstractICellCore implements QueenCell {
//    private List<ConnectedCell> connectedCells;
//    public void loadConnectedCells () {
//        connectedCells = ConnectedCell.getBranchConnectionCell(getBroodQueen().getLayer());
//    }
//    public void loadBranchCommands () {
//        for (ConnectedCell cbm : connectedCells) {
//            cbm.setAll(getCoreCell(), getBroodMother(), getBroodQueen());
//            cbm.activate();
//        }
//    }
//
//    @Override
//    public void activate() {
//        loadConnectedCells();
//        loadBranchCommands();
//    }
//}
