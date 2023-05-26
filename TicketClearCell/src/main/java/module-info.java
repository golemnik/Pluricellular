import com.golem.clearCell.ClearTCellFactory;
import com.golem.clearCell.ClearTCommandCell;
import com.golem.core.schemas.basicInterfaces.Cell;
import com.golem.ticketCell.schemas.AbstractTCellFactory;

module Pluricellular.TicketClearCell.main {
    requires ColonyCore;
    requires Pluricellular.TicketCell.main;

    uses Cell;
    uses AbstractTCellFactory;

    provides Cell with ClearTCommandCell;
    provides AbstractTCellFactory with ClearTCellFactory;
}