import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.removeKey.RemoveKeyTCellFactory;
import com.golem.removeKey.RemoveKeyTCommandCell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketRemoveKey.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with RemoveKeyTCommandCell;
    provides AbstractTCellFactory with RemoveKeyTCellFactory;
}