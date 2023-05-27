import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.removeGreater.RemoveGreaterTCellFactory;
import com.golem.removeGreater.RemoveGreaterTCommandCell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketRemoveGreater.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with RemoveGreaterTCommandCell;
    provides AbstractTCellFactory with RemoveGreaterTCellFactory;
}