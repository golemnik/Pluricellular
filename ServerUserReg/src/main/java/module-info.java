import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.regCommand.RegCellCommandFactory;
import com.golem.regCommand.RegCommandCell;
import com.golem.serverCell.serverCommands.AbstractSCellFactory;

module Pluricellular.ServerUserReg.main {
    requires ColonyCore;
    requires Pluricellular.ServerCell.main;
    requires Pluricellular.NetCell.main;

    uses Cell;
    uses AbstractSCellFactory;

    provides Cell with RegCommandCell;
    provides AbstractSCellFactory with RegCellCommandFactory;
}