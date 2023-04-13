import com.golem.core.schemas.Cell;
import com.golem.ticketCell.TicketCell;

module Pluricellular.TicketCell.main {
    requires ColonyCore;

    provides Cell with TicketCell;
}