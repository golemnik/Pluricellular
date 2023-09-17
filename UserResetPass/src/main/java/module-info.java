import com.golem.ResetCellFactory;
import com.golem.ResetCommandCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.serverCell.serverCommands.AbstractSCellFactory;

module Pluricellular.UserLogin.main {
    requires ColonyCore;
    requires Pluricellular.ServerCell.main;
    requires Pluricellular.NetCell.main;

    uses Cell;
    uses AbstractSCellFactory;

    provides Cell with ResetCommandCell;
    provides AbstractSCellFactory with ResetCellFactory;

}