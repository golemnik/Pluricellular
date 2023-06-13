import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.removeGreaterKey.RemoveGreaterKeyTCellFactory;
import com.golem.removeGreaterKey.RemoveGreaterKeyTCommandCell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketRemoveGreater.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with RemoveGreaterKeyTCommandCell;
    provides AbstractTCellFactory with RemoveGreaterKeyTCellFactory;
}
