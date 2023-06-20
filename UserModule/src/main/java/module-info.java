import com.golem.clientLoad.LoadClientCellFactory;
import com.golem.clientLoad.LoadClientCommandCell;
import com.golem.core.schemas.basicAbstractions.AbstractSystemCellFactory;
import com.golem.core.schemas.basicInterfaces.Cell;

module Pluricellular.ClientsLoad.main {
    requires ColonyCore;
    requires com.google.gson;

    uses Cell;
    uses AbstractSystemCellFactory;

    provides Cell with LoadClientCommandCell;
    provides AbstractSystemCellFactory with LoadClientCellFactory;

}