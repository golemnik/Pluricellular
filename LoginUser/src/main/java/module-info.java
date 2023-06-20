import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;

module Pluricellular.LoginUser.main {
    requires ColonyCore;

    uses Cell;
    uses AbstractSystemCellFactory;

    provides Cell with LoadClientCommandCell;
    provides AbstractSystemCellFactory with LoadClientCellFactory;

}