import com.golem.core.schemas.basicAbstractions.AbstractCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.userLoad.LoadCCellFactory;
import com.golem.userLoad.LoadCCommandCell;

module Pluricellular.UserLoad.main {
    requires ColonyCore;
    requires com.google.gson;
    requires Pluricellular.ServerCell.main;

    uses Cell;
    uses AbstractCellFactory;

    provides Cell with LoadCCommandCell;
    provides AbstractCellFactory with LoadCCellFactory;
}